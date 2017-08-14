package com.peony.peonyfront.util;

import java.util.HashSet;

/**
 * 相似度计算
 */
public class SimilarityUtil {
    public static double sim(String a, String b) {
        HashSet<Character> wordA = new HashSet<Character>();
        HashSet<Character> wordB = new HashSet<Character>();
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (!wordA.contains(a.charAt(i)))
                wordA.add(a.charAt(i));
        }
        for (int i = 0; i < b.length(); i++) {
            if (!wordB.contains(b.charAt(i))) {
                wordB.add(b.charAt(i));
                if (wordA.contains(b.charAt(i)))
                    count++;
            }
        }
        return (2.0 * count) / (wordA.size() + wordB.size() + 0.0);
    }

}
