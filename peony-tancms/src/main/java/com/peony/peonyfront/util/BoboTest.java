package com.peony.peonyfront.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.browseengine.bobo.api.BoboBrowser;
import com.browseengine.bobo.api.BoboIndexReader;
import com.browseengine.bobo.api.Browsable;
import com.browseengine.bobo.api.BrowseFacet;
import com.browseengine.bobo.api.BrowseHit;
import com.browseengine.bobo.api.BrowseRequest;
import com.browseengine.bobo.api.BrowseResult;
import com.browseengine.bobo.api.FacetAccessible;
import com.browseengine.bobo.api.FacetSpec;
import com.browseengine.bobo.api.FacetSpec.FacetSortSpec;
import com.browseengine.bobo.facets.FacetHandler;
import com.browseengine.bobo.facets.data.PredefinedTermListFactory;
import com.browseengine.bobo.facets.impl.RangeFacetHandler;
import com.browseengine.bobo.facets.impl.SimpleFacetHandler;

public class BoboTest {

    private Directory indexDir      = null;
    private Version   luceneVersion = Version.LUCENE_34;

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BoboTest app = new BoboTest();
        app.test1();
    }

    public void test1() {

        try {
            indexDir = new RAMDirectory();

            createIndex();
            searchTest();

            indexDir.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createIndex() {
        String[][] data = new String[][] { new String[] { "lenovo", "Intel", "PC Core2 E8200 2GB DDR2 250GB 7200RPM 22LCD", "08998" }, new String[] { "lenovo", "Intel", "PC Core2 E8300 2GB DDR2 320GB 7200RPM 22LCD", "09998" }, new String[] { "lenovo", "Intel", "PC Core2 Q6600 2GB DDR2 320GB 7200RPM 22LCD", "11998" }, new String[] { "lenovo", "Intel", "PC Core2 QX9770 4GB DDR2 320GB 7200RPM RAID-1 22LCD", "19998" }, new String[] { "lenovo", "Intel", "PC pentium E2200 1GB DDR2 160GB 5400RPM 19LCD", "05998" }, new String[] { "hp", "Intel", "PC pentium E2180 1GB DDR2 160GB 7200RPM 20LCD", "06398" }, new String[] { "hp", "Intel", "PC Core2 E8200 2GB DDR2 250GB 5400RPM 22LCD", "08998" }, new String[] { "hp", "Intel", "PC Core2 E6550 2GB DDR2 250GB 7200RPM 20LCD", "07398" }, new String[] { "hp", "Intel", "PC Core2 QX6850 4GB DDR2 320GB 5400RPM 22LCD", "13998" }, new String[] { "asus", "AMD", "PC Core2 QX9650 4GB DDR2 450GB 7200RPM 22LCD", "17998" },
                new String[] { "dell", "AMD", "PC Core2 athlon FX76 4GB DDR2 450GB 7200RPM 22LCD", "12998" } };

        try {
            Analyzer analyzer = new StandardAnalyzer(luceneVersion);

            // Lucene Version >= 3.2.0 (Version.LUCENE_32)
            IndexWriterConfig indexConfig = new IndexWriterConfig(luceneVersion, analyzer);
            indexConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            IndexWriter indexWriter = new IndexWriter(indexDir, indexConfig);

            // Lucene Version < 3.2.0 (Version.LUCENE_32)
            // IndexWriter indexWriter = new IndexWriter(indexDir, analyzer,
            // true, MaxFieldLength.LIMITED);

            for (int i = 0; i < data.length; i++) {
                Document doc = new Document();
                doc.add(new Field("vendor", data[i][0], Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field("cpu", data[i][1], Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field("desc", data[i][2], Field.Store.YES, Field.Index.ANALYZED));
                doc.add(new Field("price", data[i][3], Field.Store.YES, Field.Index.NOT_ANALYZED));
                indexWriter.addDocument(doc);
            }

            indexWriter.optimize();
            indexWriter.commit();
            indexWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void searchTest() {
        try {
            String fieldName = "desc";
            String keywords = "Core2";
            QueryParser queryParser = new QueryParser(luceneVersion, fieldName, new StandardAnalyzer(luceneVersion));
            Query query = queryParser.parse(keywords);

            IndexReader indexReader = IndexReader.open(indexDir, true);

            List<FacetHandler<?>> facetHandlers = new ArrayList<FacetHandler<?>>();
            facetHandlers.add(new SimpleFacetHandler("vendor"));
            facetHandlers.add(new SimpleFacetHandler("cpu"));

            // facetHandlers.add(new RangeFacetHandler("price",
            // Arrays.asList(new String[]{"[* TO 09998]", "[09999 TO 11998]",
            // "[11999 TO *]"})));
            String[] ranges = new String[] { "[00000 TO 09999]", "[10000 TO 11998]", "[11999 TO 30000]" };
            facetHandlers.add(new RangeFacetHandler("price", new PredefinedTermListFactory(Integer.class, "0"), Arrays.asList(ranges)));

            BoboIndexReader boboIndexReader = BoboIndexReader.getInstance(indexReader, facetHandlers);
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

            browseRequest.setFacetSpec("vendor", facetSpec);
            browseRequest.setFacetSpec("cpu", facetSpec);
            browseRequest.setFacetSpec("price", facetSpec);

            Browsable browser = new BoboBrowser(boboIndexReader);
            BrowseResult browseResult = browser.browse(browseRequest);

            int totalHits = browseResult.getNumHits();
            BrowseHit[] browseHit = browseResult.getHits();

            System.out.println("=====Total records: " + totalHits);

            // 获取分组统计结果
            Map<String, FacetAccessible> facetMap = browseResult.getFacetMap();
            if (facetMap.size() > 0) {
                System.out.println("-------Vendor-----------------------");

                FacetAccessible vendorFacets = facetMap.get("vendor");
                List<BrowseFacet> facetVals = vendorFacets.getFacets();
                for (BrowseFacet f : facetVals) {
                    System.out.println(f.getValue() + "(" + f.getHitCount() + ")");
                }

                System.out.println("-------CPU----------------");
                FacetAccessible cpuFacets = facetMap.get("cpu");
                facetVals = cpuFacets.getFacets();
                for (BrowseFacet f : facetVals) {
                    System.out.println(f.getValue() + "(" + f.getHitCount() + ")");
                }

                System.out.println("-------Price----------------");
                FacetAccessible priceFacets = facetMap.get("price");
                facetVals = priceFacets.getFacets();
                for (BrowseFacet f : facetVals) {
                    System.out.println(f.getValue() + "(" + f.getHitCount() + ")");
                }
            }

            boboIndexReader.close();
            indexReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
