package com.example.demo.controller;

import com.example.demo.mapper.questionMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import com.example.demo.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {
    @Autowired
    questionMapper questionMapper;
    @Autowired
    questionService questionService;
    @Autowired
    userMapper mapper;
    @RequestMapping("/publish")
        public String publish(Model model){
        model.addAttribute("tagList",questionService.getListTag());
        System.out.println(questionService.getListTag());
        return "publish";
        }

        //*发布问题
        // 表单提交*//*
    @PostMapping("/commitQuestion")
    public String commitQuestion(@RequestParam(name = "title")String title,
                                 @RequestParam(name = "description") String description,
                                 @RequestParam(name = "tag")String tag,
                                 @RequestParam(name = "id")String  id,
                                 HttpServletRequest request, Model model){

        if (title==null || title==""){
            model.addAttribute("title","标题不能为空");
            return "publish";
        }
        if (description==null || description==""){
            model.addAttribute("description","描述不能为空");
            return "publish";
        }
        if (tag==null || tag==""){
            model.addAttribute("tag","目标不能为空");
            return "publish";
        }

        user user=null;
        user = (user) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","当前没有登入");
            return "publish";
        }

        model.addAttribute("tagList",questionService.getListTag());

            question question = new question();
            question.setTitle(title);
            question.setDescription(description);
            System.out.println("tag"+tag);
            question.setTag(tag);
            question.setCreator(user.getId());
            System.out.println(question);
           questionService.updateOrCreate(question);
            return "redirect:/";
    }


}
