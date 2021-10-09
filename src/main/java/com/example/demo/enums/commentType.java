package com.example.demo.enums;

public enum commentType {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public Integer getType() {
        return type;
    }

    commentType(Integer type) {
        this.type = type;
    }
}
