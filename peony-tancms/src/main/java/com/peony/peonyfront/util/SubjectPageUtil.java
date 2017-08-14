package com.peony.peonyfront.util;

import com.peony.peonyfront.subject.model.SubjectPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by htan on 2016/4/8.
 */
public class SubjectPageUtil {
    // 如果第i个元素跟第i-1个元素相同，就把重复的去掉
    public static List<SubjectPage> removeDuplicateByTitle(List<SubjectPage> list) {
        if (null == list || list.size() <= 1) {
            return list;
        }
        List<SubjectPage> newList = new ArrayList<>();
        newList.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            String curTitle = list.get(i).getTitle();
            String lastTitle = list.get(i - 1).getTitle();

            if (!curTitle.equals(lastTitle)) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    // 修改SubjectPage的URL，如果不以http开头，就换成快照的URL
    public static String correctURL(String url) {
        if (!url.startsWith("http")) {
            url = "loadSnapshot?pageid=" + url;
        }
        return url;
    }
}
