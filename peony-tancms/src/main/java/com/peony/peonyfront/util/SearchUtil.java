package com.peony.peonyfront.util;

import com.browseengine.bobo.api.*;
import com.browseengine.bobo.api.FacetSpec.FacetSortSpec;
import com.browseengine.bobo.facets.FacetHandler;
import com.browseengine.bobo.facets.impl.SimpleFacetHandler;
import com.peony.peonyfront.region.model.Region;
import org.apache.lucene.index.*;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * lucene查询
 *
 * @author jhj
 */
public class SearchUtil {

    private static final Logger log = LoggerFactory.getLogger(SearchUtil.class);
    LuceneUtil lu = new LuceneUtil();

    /**
     * 查询api
     *
     * @param query
     * @return
     * @throws IOException
     * @throws CorruptIndexException
     */
    private int getCounts(Query query, String userId) throws CorruptIndexException, IOException {
        int num = 0;
        IndexSearcher searcher = this.lu.getIndexSearcher(userId);
        if (searcher != null) {
            try {
                TopDocs hits = searcher.search(query, 1);
                num = hits.totalHits;
            } catch (Exception e) {
                log.error("", e);
            } finally {
                this.lu.close(this.lu.getIndexReader(userId));
            }
        }
        return num;
    }

    private static Query getRegionIdTermQuery(String[] keyword) {
        BooleanQuery bq = new BooleanQuery();
        for (String key : keyword) {
            PrefixQuery preq = new PrefixQuery(new Term("regionId", key));
            bq.add(preq, Occur.SHOULD);
        }
        return bq;
    }

    private static Query getRegionTermQuery(Region region) {
        BooleanQuery bq = new BooleanQuery();
        bq.add(new TermQuery(new Term("title", region.getRegionname())), Occur.SHOULD);
        bq.add(new TermQuery(new Term("content", region.getRegionname())), Occur.SHOULD);
        bq.add(new TermQuery(new Term("title", region.getRegionabbr())), Occur.SHOULD);
        bq.add(new TermQuery(new Term("content", region.getRegionabbr())), Occur.SHOULD);
        return bq;
    }

    // 新闻媒体类型
    private static Query getMediaTermQuery(String[] type) {
        BooleanQuery bq = new BooleanQuery();
        for (String key : type) {
            bq.add(new TermQuery(new Term("type", key)), Occur.SHOULD);
        }
        return bq;
    }

    // 政务
    private static Query getEventTermQuery(String[] type) {
        BooleanQuery bq = new BooleanQuery();
        for (String key : type) {
            bq.add(new TermQuery(new Term("eventId", key)), Occur.SHOULD);
        }
        return bq;
    }

    // 定制
    private static Query getSubjectTermQuery(String[] type) {
        BooleanQuery bq = new BooleanQuery();
        for (String key : type) {
            bq.add(new TermQuery(new Term("subjectId", key)), Occur.SHOULD);
        }
        return bq;
    }

    /**
     * 返回时间段查询 publishDate
     *
     * @param startTime
     * @param endTime
     * @return Query
     */
    private static Query getTimeRangeQuery(String startTime, String endTime) {
        startTime = dateFormat(startTime);
        endTime = dateFormat(endTime);
        TermRangeQuery query = new TermRangeQuery("publishDate", startTime, endTime, true, true);
        return query;
    }

    // 定制 政务 按时间段分别统计 趋势分析
    // module 1定制 2.政务
    public Map searchCount(String userId, String module, String regionId[], String time[], String type[], String event[], String subject[]) throws CorruptIndexException, IOException {
        Map<String, Integer> map = new HashMap();

        for (String t : time) {
            BooleanQuery bq = new BooleanQuery();
            bq.add(new TermQuery(new Term("userId", userId)), Occur.MUST);
            if (module != null) {
                bq.add(new TermQuery(new Term("module", module)), Occur.MUST);
                if (module.equals("2")) {
                    if (event != null) {
                        bq.add(getEventTermQuery(event), Occur.MUST);
                    }
                    if (regionId != null) {
                        bq.add(getRegionIdTermQuery(regionId), Occur.MUST);
                    }
                } else {
                    if (subject != null) {
                        bq.add(getSubjectTermQuery(subject), Occur.MUST);
                    }

                }
            }

            bq.add(getTimeRangeQuery(t + " 00:00:00", t + " 23:59:59"), Occur.MUST);
            if (type != null) {
                bq.add(getMediaTermQuery(type), Occur.MUST);
            }
            int m = getCounts(bq, userId);
            map.put(t, m);
        }
        // System.out.println("趋势分析："+map);
        return map;
    }

    public Query getQuery(String userId, String module[], String regionId[], String startTime, String endTime, String type[], String event[], String subject[]) {

        BooleanQuery bq = new BooleanQuery();

        bq.add(new TermQuery(new Term("userId", userId)), Occur.MUST);
        if (module != null) {
            BooleanQuery bm = new BooleanQuery();
            for (String m : module) {
                if (m.equals("2")) {
                    BooleanQuery bk = new BooleanQuery();
                    if (event != null) {
                        bk.add(getEventTermQuery(event), Occur.MUST);
                    }
                    if (regionId != null) {
                        bk.add(getRegionIdTermQuery(regionId), Occur.MUST);
                    }
                    bm.add(bk, Occur.SHOULD);
                } else {
                    BooleanQuery bk = new BooleanQuery();
                    if (subject != null) {
                        bk.add(getSubjectTermQuery(subject), Occur.MUST);
                    }

                    bm.add(bk, Occur.SHOULD);
                }
            }
            bq.add(bm, Occur.MUST);
        }

        bq.add(getTimeRangeQuery(startTime + " 00:00:00", endTime + " 23:59:59"), Occur.MUST);
        if (type != null) {
            bq.add(getMediaTermQuery(type), Occur.MUST);
        }
        return bq;
    }

    // 定制 政务 按时间段分别统计 媒体分布
    // module 1定制 2.政务
    public Map mediaCount(String userId, String module, String regionId[], String startTime, String endTime, String type[], String event[], String subject[]) throws CorruptIndexException, IOException {
        Map<String, Integer> map = new HashMap();
        for (String t : type) {
            BooleanQuery bq = new BooleanQuery();
            bq.add(new TermQuery(new Term("userId", userId)), Occur.MUST);
            if (module != null) {
                bq.add(new TermQuery(new Term("module", module)), Occur.MUST);
                if (module.equals("2")) {
                    if (event != null) {
                        bq.add(getEventTermQuery(event), Occur.MUST);
                    }
                    if (regionId != null) {
                        bq.add(getRegionIdTermQuery(regionId), Occur.MUST);
                    }
                } else {
                    if (subject != null) {
                        bq.add(getSubjectTermQuery(subject), Occur.MUST);
                    }

                }
            }

            bq.add(new TermQuery(new Term("type", t)), Occur.MUST);

            if (startTime != null && endTime != null) {
                bq.add(getTimeRangeQuery(startTime + " 00:00:00", endTime + " 23:59:59"), Occur.MUST);
            }
            int m = getCounts(bq, userId);
            map.put(t, m);
        }
        // System.out.println("媒体分布："+map);
        return map;
    }

    /**
     * 情感分析媒体-统计 媒体类型对比
     *
     * @param userId
     * @param module
     * @param region
     * @param startTime
     * @param endTime
     * @param type
     * @param event
     * @param subject
     * @return
     * @throws IOException
     * @throws CorruptIndexException
     */
    public Map emotionMediaCount(String userId, String module, String regionId[], String startTime, String endTime, String type, String event[], String subject[]) throws CorruptIndexException, IOException {
        Map<String, Integer> map = new HashMap();
        String emotion[] = {"0", "1", "-1"};
        for (String polarity : emotion) {
            BooleanQuery bq = new BooleanQuery();
            bq.add(new TermQuery(new Term("userId", userId)), Occur.MUST);
            if (module != null) {
                bq.add(new TermQuery(new Term("module", module)), Occur.MUST);
                if (module.equals("2")) {
                    if (event != null) {
                        bq.add(getEventTermQuery(event), Occur.MUST);
                    }
                    if (regionId != null) {
                        bq.add(getRegionIdTermQuery(regionId), Occur.MUST);
                    }
                } else {
                    if (subject != null) {
                        bq.add(getSubjectTermQuery(subject), Occur.MUST);
                    }

                }
            }
            bq.add(new TermQuery(new Term("polarity", polarity)), Occur.MUST);
            bq.add(getTimeRangeQuery(startTime + " 00:00:00", endTime + " 23:59:59"), Occur.MUST);
            if (type != null) {
                bq.add(new TermQuery(new Term("type", type)), Occur.MUST);
            }
            int m = getCounts(bq, userId);
            map.put(polarity, m);// 返回结果为 key:新闻极性 value:数量
        }
        // System.out.println("正负面媒体趋势分析："+map);
        return map;
    }

    /**
     * 情感分析统计 正负面倾向分析
     *
     * @param userId
     * @param module
     * @param region
     * @param startTime
     * @param endTime
     * @param type
     * @param event
     * @param subject
     * @return
     * @throws IOException
     * @throws CorruptIndexException
     */
    public Map emotionCount(String userId, String module, String regionId[], String startTime, String endTime, String type[], String event[], String subject[]) throws CorruptIndexException, IOException {
        Map<String, Integer> map = new HashMap();
        String emotion[] = {"0", "1", "-1"};
        for (String polarity : emotion) {
            BooleanQuery bq = new BooleanQuery();
            bq.add(new TermQuery(new Term("userId", userId)), Occur.MUST);
            if (module != null) {
                bq.add(new TermQuery(new Term("module", module)), Occur.MUST);
                if (module.equals("2")) {
                    if (event != null) {
                        bq.add(getEventTermQuery(event), Occur.MUST);
                    }
                    if (regionId != null) {
                        bq.add(getRegionIdTermQuery(regionId), Occur.MUST);
                    }
                } else {
                    if (subject != null) {
                        bq.add(getSubjectTermQuery(subject), Occur.MUST);
                    }

                }
            }
            bq.add(new TermQuery(new Term("polarity", polarity)), Occur.MUST);
            bq.add(getTimeRangeQuery(startTime + " 00:00:00", endTime + " 23:59:59"), Occur.MUST);
            if (type != null) {
                bq.add(getMediaTermQuery(type), Occur.MUST);
            }
            int m = getCounts(bq, userId);
            map.put(polarity, m);// 返回结果为 key:新闻极性 value:数量
        }
        // System.out.println("正负面分析："+map);
        return map;
    }

    /**
     * 网站排行
     *
     * @param query
     * @return
     */
    public String webSiteCount(Query query, String userId) throws IOException, BrowseException {
        String result = "";

        List<FacetHandler<?>> facetHandlers = new ArrayList<FacetHandler<?>>();
        facetHandlers.add(new SimpleFacetHandler("webSite"));

        // facetHandlers.add(new RangeFacetHandler("price", Arrays.asList(new
        // String[]{"[* TO 09998]", "[09999 TO 11998]", "[11999 TO *]"})));
        // facetHandlers.add(new RangeFacetHandler("price", new
        // PredefinedTermListFactory(Integer.class, "0"),
        // Arrays.asList(ranges)));

        BoboIndexReader boboIndexReader = null;
        try {
            boboIndexReader = BoboIndexReader.getInstance(this.lu.getIndexReader(userId), facetHandlers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BrowseRequest browseRequest = new BrowseRequest();
        browseRequest.setCount(10);
        browseRequest.setOffset(0);

        browseRequest.setQuery(query);

        // SortField[] sortFields = new SortField[2]; //排序
        // sortFields[0] = new SortField("vendor", SortField.STRING, true);
        // sortFields[1] = new SortField("price", SortField.INT, true);
        // browseRequest.setSort(sortFields);

        FacetSpec facetSpec = new FacetSpec();
        facetSpec.setMaxCount(10);// 搜索出来的标签数目
        facetSpec.setOrderBy(FacetSortSpec.OrderHitsDesc);

        browseRequest.setFacetSpec("webSite", facetSpec);

        Browsable browser = new BoboBrowser(boboIndexReader);
        BrowseResult browseResult = browser.browse(browseRequest);

        /*
         * int totalHits = browseResult.getNumHits(); BrowseHit[] browseHit =
         * browseResult.getHits();
         */

        // System.out.println("=====Total records: "+totalHits);

        // 获取分组统计结果
        Map<String, FacetAccessible> facetMap = browseResult.getFacetMap();
        String name = "";
        String value = "";
        if (facetMap.size() > 0) {
            // System.out.println("-------webSite-----------------------");

            FacetAccessible vendorFacets = facetMap.get("webSite");
            List<BrowseFacet> facetVals = vendorFacets.getFacets();
            for (BrowseFacet f : facetVals) {
                // System.out.println(f.getValue() + "(" + f.getHitCount() +
                // ")");
                if ("".equals(name)) {
                    name = "'" + f.getValue() + "'";
                    value = Integer.toString(f.getHitCount());
                } else {
                    name = name + ",'" + f.getValue() + "'";
                    value = value + "," + Integer.toString(f.getHitCount());
                }
            }

        }

        boboIndexReader.close();
        this.lu.close(this.lu.getIndexReader(userId));
        return name + "&" + value;
    }

    public Map hotspot(String userId) throws IOException {
        List<Term_> list = new ArrayList();
        IndexReader reader = this.lu.getIndexReader(userId);
        int totalDoc = reader.numDocs();// 总文档数
        TermEnum termEnum = reader.terms(); // 获取所有term
        int allTf = 0;// 总词数
        while (termEnum.next()) {
            Term t = termEnum.term();
            if (t.field().equals("title")) {
                if (termEnum.term().text().length() > 1 && isContainChinese(termEnum.term().text())) {
                    allTf++;
                    Term_ term = new Term_();
                    term.setDf(termEnum.docFreq());
                    term.setIdf((double) Math.log(totalDoc / termEnum.docFreq()));
                    TermPositions termPositions = reader.termPositions(termEnum.term());
                    int tf = 0;
                    while (termPositions.next()) {
                        // System.out.println("\n"+(i++)+"->"+"
                        // 文章编号:"+termPositions.doc()+",
                        // 出现次数:"+termPositions.freq());
                        tf = tf + termPositions.freq();
                    }
                    term.setTf(tf);
                    // term.setName(termEnum.term().text() +" tf: "+tf +" df:
                    // "+term.getDf()+" idf: ");
                    term.setName(termEnum.term().text());
                    if (termEnum.term().text().length() > 1 && isContainChinese(termEnum.term().text())) {
                        list.add(term);
                    }
                }
            }
        }
        this.lu.close(this.lu.getIndexReader(userId));
        Map map = new HashMap();
        for (Term_ t : list) {
            double tf_idf = (((double) t.getTf() / allTf)) * t.getIdf();
            t.setTf_idf(tf_idf);
            map.put(t.getName(), t.getTf_idf());
        }

        // List m= sortMap(map);
        // printMap(m);
        return map;

    }

    /**
     * 地域统计
     *
     * @param userId
     * @param regionArry
     * @return Map 返回结果为 key:地域词 value:数量
     */
    public Map regionCount(String userId, Region regionArry[]) throws CorruptIndexException, IOException {
        Map<String, Integer> map = new HashMap();
        for (Region r : regionArry) {
            BooleanQuery bq = new BooleanQuery();
            bq.add(getRegionTermQuery(r), Occur.MUST);
            int m = getCounts(bq, userId);
            map.put(r.getRegionname(), m);// 返回结果为 key:地域词 value:数量
        }
        // System.out.println("地域统计分析："+map);
        return map;
    }

    public static List sortMap(Map oldMap) {
        List<Map.Entry<String, Double>> list_Data = new ArrayList<Map.Entry<String, Double>>(oldMap.entrySet());
        Collections.sort(list_Data, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if ((o2.getValue() - o1.getValue()) > 0) {
                    return 1;
                } else if ((o2.getValue() - o1.getValue()) == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }

        });
        return list_Data;
    }

    private static void printMap(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * 将时间字符串格式化为 短格式'20140101030303' lucene识别
     *
     * @param String date
     * @return
     */
    public static String dateFormat(String date) {
        String dest = "";
        if (date != null) {
            Pattern p = Pattern.compile("[-|:|\\s]*");
            Matcher m = p.matcher(date);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static boolean isContainChinese(String chineseContent) {
        if (chineseContent == null)
            return false;
        return chineseContent.matches("[\u4e00-\u9fa5]+");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        String s = "河北省";
        boolean k = s.lastIndexOf("省") > 0;
        System.out.println(k);
    }

}
