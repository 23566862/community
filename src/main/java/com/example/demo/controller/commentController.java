package com.example.demo.controller;

import com.example.demo.dto.ResultCodeDTO;
import com.example.demo.dto.commentDTO;

import com.example.demo.mapper.commentMapper;
import com.example.demo.mapper.questionMapper;
import com.example.demo.pojo.comment;
import com.example.demo.pojo.user;
import com.example.demo.service.commentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class commentController {

    @Autowired
    private commentService commentService;
    @Autowired
    private com.example.demo.mapper.commentMapper commentMapper;

    /*一级评论二级评论*/
    @RequestMapping("/comment")
    @ResponseBody
    public Object comment(  commentDTO commentDTO,
                          HttpServletRequest request){
        user user =(user) request.getSession().getAttribute("user");
        comment comment = new comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setCommentator(user.getId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setContent(commentDTO.getContent());
        commentService.insert(comment);
        HashMap<String, Object> hashMap = new HashMap<>();
        return hashMap;
    }


    /*一级评论二级评论*/
    @RequestMapping("/twoComment")
    @ResponseBody
    public Object twoComment(@RequestParam("id") int id,@RequestParam("type") int type
           ){

        List<comment> comment = commentMapper.getComment(id, type);
        System.out.println(comment);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data",comment);
        return  hashMap;
    }


}
