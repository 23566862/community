package com.example.demo.mapper;

import com.example.demo.pojo.comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  commentMapper {
    int insert(comment comment);
    List<comment> getComment(@Param("id")int id,@Param("type")int type);

}
