package com.peony.peonyfront.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import md.base.storage.HttpPageStorage;
import md.base.storage.ha.HttpHaSimiPageStorage;

/**
 * 快照接口类
 * 
 * @author pc
 * 
 */
public class Snapshot {

    /**
     * 读取纯文本 读纯文本的完整代码示例： WebPageStorage storage = new WebPageStorage(); String
     * baseUrl = "http://119.254.110.32:8080/HBaseDfs/dfs"
     * storage.useHttpFileSystem(baseUrl);
     * 
     * boolean pureText = true; String content = storage.get(urlMd5, pureText);
     */
    public static String getHTMLContent(String pageId) {

        HttpHaSimiPageStorage storage = new HttpHaSimiPageStorage();
        HttpPageStorage storage0;
        HttpPageStorage storage1;
        try {
            storage0 = new HttpPageStorage("http://119.254.110.32:8080/HBaseDfs0/dfs", "key");
            storage1 = new HttpPageStorage("http://119.254.110.32:8082/HBaseDfs0/dfs", "key");
            storage.Add(storage0);
            storage.Add(storage1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String content = "";
        try {
            // 通过URL产生MD5。如果从数据库中获取ID，直接传入数据库的id字段即可
            // String url = "http://news.sohu.com/20140716/n402322522.shtml";
            // String html = TestCase.RandomString(20480);
            // String urlMd5 = Misc.MD5(url);
            // String urlMd5 = "13C158F4FD1F840108342504E942164E";
            // 写文件
            // boolean success = storage.put(urlMd5, html, pureText); //写纯文本
            // boolean success = storage.put(urlMd5, html); //写HTML
            // System.out.println(success);

            // 读文件
            // boolean pureText = true; //是否读取纯文本
            // String content = storage.get(urlMd5, pureText); //读纯文本
            content = storage.get(pageId); // 读HTML
            // 删除文件
            // storage.delete(urlMd5); //删除HTML
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                storage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 读取纯文本
     * 
     * @param pageId
     * @return
     */
    public static String getTestContent(String pageId) {
        HttpHaSimiPageStorage storage = new HttpHaSimiPageStorage();
        HttpPageStorage storage0;
        HttpPageStorage storage1;
        Map<String, String> map = getContentHtmlMap();
        try {
            storage0 = new HttpPageStorage("http://119.254.110.32:8080/HBaseDfs0/dfs", "key");
            storage1 = new HttpPageStorage("http://119.254.110.32:8082/HBaseDfs0/dfs", "key");
            storage.Add(storage0);
            storage.Add(storage1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String content = "";
        try {
            boolean pureText = true; // 是否读取纯文本
            content = storage.get(pageId, pureText); // 读纯文本

            // 根据文本中的换行符\n,\r,html特殊字符以及空格进行换行
            for (String key : map.keySet()) {
                content = content.replace("" + key + "", "" + map.get(key) + "");
            }
            content = content.replace(" ", "<w:br />　　").replace("\n", "<w:br />　　").replace("\r", "<w:br />　　").replaceAll("(<w:br />　　)+", "<w:br />　　").replace("<w:br />　　　　", "<w:br />　　");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                storage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * HTML特殊字符编码Map
     * 
     * @return
     */
    public static Map<String, String> getContentHtmlMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("&acute;", "´");
        map.put("&copy;", "©");
        map.put("&gt;", ">");
        map.put("&micro;", "µ");
        map.put("&reg;", "®");
        map.put("&amp;", "&");
        map.put("&deg;", "°");
        map.put("&iexcl;", "¡");
        map.put("&nbsp;", " ");
        map.put("&raquo;", "»");
        map.put("&brvbar;", "¦");
        map.put("&divide;", "÷");
        map.put("&iquest;", "¿");
        map.put("&not;", "¬");
        map.put("&sect;", "§");
        map.put("&bull;", "•");
        map.put("&frac12;", "½");
        map.put("&laquo;", "«");
        map.put("&para;", "¶");
        map.put("&uml;", "¨");
        map.put("&cedil;", "¸");
        map.put("&frac14;", "¼");
        map.put("&lt;", "<");
        map.put("&plusmn;", "±");
        map.put("&times;", "×");
        map.put("&cent;", "¢");
        map.put("&frac34;", "¾");
        map.put("&macr;", "¯");
        map.put("&quot;", "“");
        map.put("&trade;", "™");
        map.put("&euro;", "€");
        map.put("&pound;", "£");
        map.put("&yen;", "¥");
        map.put("&bdquo;", "„");
        map.put("&hellip;", "…");
        map.put("&middot;", "·");
        map.put("&rsaquo;", "›");
        map.put("&ordf;", "ª");
        map.put("&circ;", "ˆ");
        map.put("&ldquo;", "“");
        map.put("&mdash;", "—");
        map.put("&rsquo;", "’");
        map.put("&ordm;", "º");
        map.put("&dagger;", "†");
        map.put("&lsaquo;", "‹");
        map.put("&ndash;", "–");
        map.put("&sbquo;", "‚");
        map.put("&rdquo;", "”");
        map.put("&Dagger;", "‡");
        map.put("&lsquo;", "‘");
        map.put("&permil;", "‰");
        map.put("&shy;", " ");
        map.put("&tilde;", "˜");
        map.put("&asymp;", "≈");
        map.put("&frasl;", "⁄");
        map.put("&larr;", "←");
        map.put("&part;", "∂");
        map.put("&spades;", "♠");
        map.put("&cap;", "∩");
        map.put("&ge;", "≥");
        map.put("&le;", "≤");
        map.put("&Prime;", "″");
        map.put("&sum;", "∑");
        map.put("&clubs;", "♣");
        map.put("&harr;", "↔");
        map.put("&loz;", "◊");
        map.put("&prime;", "′");
        map.put("&uarr;", "↑");
        map.put("&darr;", "↓");
        map.put("&hearts;", "♥");
        map.put("&minus;", "−");
        map.put("&prod;", "∏");
        map.put("&zwj;", " ");
        map.put("&diams;", "♦");
        map.put("&infin;", "∞");
        map.put("&ne;", "≠");
        map.put("&radic;", "√");
        map.put("&zwnj;", "");
        map.put("&equiv;", "≡");
        map.put("&int;", "∫");
        map.put("&oline;", "‾");
        map.put("&rarr;", "→");
        map.put("&alpha;", "α");
        map.put("&eta;", "η");
        map.put("&mu;", "μ");
        map.put("&pi;", "π");
        map.put("&theta;", "θ");
        map.put("&beta;", "β");
        map.put("&gamma;", "γ");
        map.put("&nu;", "ν");
        map.put("&psi;", "ψ");
        map.put("&upsilon;", "υ");
        map.put("&chi;", "χ");
        map.put("&iota;", "ι");
        map.put("&omega;", "ω");
        map.put("&rho;", "ρ");
        map.put("&xi;", "ξ");
        map.put("&delta;", "δ");
        map.put("&kappa;", "κ");
        map.put("&omicron;", "ο");
        map.put("&sigma;", "σ");
        map.put("&zeta;", "ζ");
        map.put("&epsilon;", "ε");
        map.put("&lambda;", "λ");
        map.put("&phi;", "φ");
        map.put("&tau;", "τ");
        map.put("&Alpha;", "Α");
        map.put("&sigmaf;", "ς");
        map.put("&Tau;", "Τ");
        map.put("&Phi;", "Φ");
        map.put("&Lambda;", "Λ");
        map.put("&Sigma;", "Σ");
        map.put("&Delta;", "Δ");
        map.put("&Xi;", "Ξ");
        map.put("&Omega;", "Ω");
        map.put("&Psi;", "Ψ");
        map.put("&Gamma;", "Γ");
        map.put("&ensp;", "");
        map.put("&emsp;", "");
        map.put("&Epsilon;", "Ε");
        map.put("&Tau;", "Τ");
        map.put("&Zeta;", "Ζ");
        map.put("&Omicron;", "Ο");
        map.put("&Kappa;", "Κ");
        map.put("&Rho;", "Ρ");
        map.put("&Iota;", "Ι");
        map.put("&Chi;", "Χ");
        map.put("&Upsilon;", "Υ");
        map.put("&Nu;", "Ν");
        map.put("&Beta;", "Β");
        map.put("&Mu;", "Μ");
        map.put("&Eta;", "Η");
        map.put("&nbsp", "");
        return map;
    }
}
