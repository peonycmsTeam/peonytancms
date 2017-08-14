package com.peony.peonyfront.util;

public class Term_ {

    public String name;
    public int    tf;    // 词频
    public int    df;    // 文档
    public double idf;
    public double tf_idf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    public int getDf() {
        return df;
    }

    public void setDf(int df) {
        this.df = df;
    }

    public double getIdf() {
        return idf;
    }

    public void setIdf(double idf) {
        this.idf = idf;
    }

    public double getTf_idf() {
        return tf_idf;
    }

    public void setTf_idf(double tf_idf) {
        this.tf_idf = tf_idf;
    }

}
