package com.example.demo.dto;

import lombok.Data;

@Data
public class commentDTO {
    private Long id;
    private int parentId;
    private Integer type;
    private Long commentator;
    private String content;
    private Long gmtCreate;
    private Long gmtModified;
}
