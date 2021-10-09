package com.example.demo.dto;

import lombok.Data;

@Data
public class ResultCodeDTO {
    private Integer code;
    private String message;

    public static ResultCodeDTO errOf(Integer code, String message){
        ResultCodeDTO resultCode = new ResultCodeDTO();
        resultCode.setCode(code);
        resultCode.setMessage(message);
        return resultCode;
    }
}
