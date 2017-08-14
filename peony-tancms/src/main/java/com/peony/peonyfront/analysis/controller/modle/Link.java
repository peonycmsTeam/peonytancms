package com.peony.peonyfront.analysis.controller.modle;

public class Link {
    private String source;
    private String target;
    private String weight;

    public Link(String source, String target, String weight) {

        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
