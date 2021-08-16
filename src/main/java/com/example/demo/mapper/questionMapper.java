package com.example.demo.mapper;

import com.example.demo.pojo.question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface questionMapper {
    int addQuestion(question question);
    List<question> getList();
}
