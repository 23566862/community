package com.example.demo.exception;

public enum errorCode implements IerrorCode {
    QUESTION_NOT_FOUND(2001,"你点击的问题找不到,换一个试试！"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何评论或问题进行评论");
    errorCode(Integer code,String message) {
        this.message = message;
        this.code=code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;
}
