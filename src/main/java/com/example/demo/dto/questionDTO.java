package com.example.demo.dto;

import com.example.demo.pojo.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class questionDTO {
    private int id;
    private String title;
    private  String description;
    private int creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private user user;
}
