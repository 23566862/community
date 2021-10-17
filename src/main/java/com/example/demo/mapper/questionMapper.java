package com.example.demo.mapper;

import com.example.demo.dto.questionDTO;
import com.example.demo.pojo.comment;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface questionMapper {
    int addQuestion(question question);
    List<question> getList(@Param("pag") int pag,@Param("size") int size);
    int getAllCount();
    List<question> getListByCreator(@Param("id") int id, @Param("pag") int pag, @Param("size") int size);
    int getAllCountById(@Param("id") int id);
    question getQuestionById(@Param("id") int id);

    int updateQuestion(question question);
    int updateCommentCount(@Param("id")int id);
    List<question> getWhereByTag(question question);
    List<question> getQuestionForeach(@Param("list") List<Integer> list);
}
