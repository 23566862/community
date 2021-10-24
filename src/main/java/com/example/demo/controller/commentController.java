package com.example.demo.controller;

import com.example.demo.dto.ResultCodeDTO;
import com.example.demo.dto.commentDTO;

import com.example.demo.mapper.commentMapper;
import com.example.demo.mapper.questionMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.comment;
import com.example.demo.pojo.notification;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import com.example.demo.service.commentService;
import com.example.demo.service.notificationService;
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
    private notificationService notificationService;
    @Autowired
    private com.example.demo.mapper.commentMapper commentMapper;
    @Autowired
    private questionMapper questionMapper;
    @Autowired
    private userMapper userMapper;
    /*一级评论二级评论*/
    @RequestMapping("/twoComment")
    @ResponseBody
    public Object comment( commentDTO commentDTO,
                          HttpServletRequest request){
        //user 当前登入用户user，发送者
        user user =(user) request.getSession().getAttribute("user");
        System.out.println(commentDTO);
        comment comment = new comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setCommentator(user.getId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setContent(commentDTO.getContent());
        //回复消息添加
        notification notification = new notification();
        notification.setGmt_create(System.currentTimeMillis());
        notification.setNotifier(user.getId());
        question questionById = questionMapper.getQuestionById(commentDTO.getParentId());
        //user1 接收者user
        user user1 = userMapper.finById(questionById.getCreator());
        notification.setReceiver(user1.getId());
        notification.setType(1);//typ 1级评论
        notification.setOuterid(questionById.getId());//问题id
        notification.setStatus(0);
        int i = notificationService.addNotification(notification);
        System.out.println(notification);
        commentService.insert(comment);
        HashMap<String, Object> hashMap = new HashMap<>();
        return hashMap;
    }


    /*一级评论二级评论*//*
    @RequestMapping("/twoComment")
    @ResponseBody
    public Object twoComment(@RequestParam("id") int id,@RequestParam("type") int type
           ){

        List<comment> comment = commentMapper.getComment(id, type);
        System.out.println(comment);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data",comment);
        return  hashMap;
    }*/


}
