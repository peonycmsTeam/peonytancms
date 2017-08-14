package com.peony.peonyfront.analysis.controller.modle;

public class Node {
    private String name;
    private String value;
    private String id;
    private String depth;
    private int    category;

    {
        this.category = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepth() {
        return this.depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
