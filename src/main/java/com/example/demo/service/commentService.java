package com.example.demo.service;

import com.example.demo.exception.errorCode;
import com.example.demo.exception.statusException;
import com.example.demo.mapper.commentMapper;
import com.example.demo.mapper.questionMapper;
import com.example.demo.pojo.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentService {
    @Autowired
    private commentMapper commentMapper;
    @Autowired
    private questionMapper questionMapper;
    public int insert(comment comment) {
        if (comment ==null || comment.getParentId() ==0){
            throw new statusException(errorCode.TARGET_PARAM_NOT_FOUND);
        }
        System.out.println(comment);
        //回复+1
        int insert = commentMapper.insert(comment);
        //回复数加1
        questionMapper.updateCommentCount(comment.getParentId());

        return insert;
    }




}
