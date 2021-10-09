package com.example.demo.pojo;

import lombok.Data;

@Data
public class comment {
    private String name;
    private String img;
    private long id;
    private int parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private Integer commentCount;
}
