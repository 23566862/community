package com.example.demo.pojo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class question {
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


}
