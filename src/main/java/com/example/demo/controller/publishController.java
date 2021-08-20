package com.example.demo.controller;

import com.example.demo.mapper.questionMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
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
    userMapper mapper;
    @RequestMapping("/publish")
        public String publish(){
        return "publish";
        }

        //*表单提交*//*
    @PostMapping("/commitQuestion")
    public String commitQuestion(@RequestParam(name = "title")String title,
                                 @RequestParam(name = "description") String description,
                                 @RequestParam(name = "tag")String tag,
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
        //根据cookie获取用户
        Cookie[] cookies = request.getCookies();
        if (cookies !=null && cookies.length !=0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")){
                    String name = cookie.getValue();
                    user = mapper.finByToken(name);
                    if (user !=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        if (user==null){
            model.addAttribute("error","当前没有登入");
            return "publish";
        }

            question question = new question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.addQuestion(question);
            return "redirect:/";




    }
}
