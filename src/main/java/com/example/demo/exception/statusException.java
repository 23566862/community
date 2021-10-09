package com.example.demo.exception;

public class statusException extends RuntimeException {
    private String message;
    private Integer code;
    public statusException(IerrorCode ierrorCode){
        this.code=ierrorCode.getCode();
        this.message=ierrorCode.getMessage();
    }


    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
