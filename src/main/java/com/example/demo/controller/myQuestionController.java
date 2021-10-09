package com.example.demo.controller;

import com.example.demo.dto.pagInitDTO;
import com.example.demo.dto.questionDTO;
import com.example.demo.mapper.commentMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.comment;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import com.example.demo.service.commentService;
import com.example.demo.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class myQuestionController {
    @Autowired
    private userMapper mapper;
    @Autowired
    private questionService questionService;
    @Autowired
    private commentService commentService;
    /*我的问题页面展示*/
    @RequestMapping("/question/{action}")
    public String AllQuestion(HttpServletRequest request,
                           @PathVariable(name = "action") String action,
                           Model model,
                           @RequestParam(value = "pag",defaultValue = "1") Integer pag,
                           @RequestParam(value = "size",defaultValue = "5") Integer size){
        user user=null;
        user = (user) request.getSession().getAttribute("user");

        if ("question".equals(action)){
            model.addAttribute("select","question");
            model.addAttribute("selectName","我的提问");
        }

        if ("reply".equals(action)){
            model.addAttribute("select","reply");
            model.addAttribute("selectName","我的回复");
        }
        System.out.println("user:"+user);
        pagInitDTO pagInitDTO = questionService.getlistByCreator(user.getId(), pag, size);
        model.addAttribute("questionList",pagInitDTO);
        return "myQuestion";
    }


    /*问题详情页*/
    @RequestMapping("/UseQuestion/{id}")
    public String UseQuestion(@PathVariable(name = "id") int id, Model model){
        //阅读数+1
        questionService.incViewCount(id);
        questionDTO questionById = questionService.getQuestionById(id);
        //相关问题查询
        question question = new question();
        question.setId(questionById.getId());
        String[] tagArray = StringUtils.split(questionById.getTag(), ",");
        String tag = Arrays.stream(tagArray).collect(Collectors.joining("|"));
        question.setTag(tag);
        List<question> whereByTag = questionService.getWhereByTag(question);
        //id查询所有的问题
        List<comment> commentList = questionService.getCommentList(id,1);
        model.addAttribute("whereByTag",whereByTag);
        model.addAttribute("commentList",commentList);
        model.addAttribute("question",questionById);
        return "useQuestion";
    }

    /*问题更新*/
    @RequestMapping("/updateQuestion/{id}")
    public String updateQuestion(@PathVariable(name ="id") int id, Model model){
        questionDTO questionById = questionService.getQuestionById(id);
        model.addAttribute("Title",questionById.getTitle());
        model.addAttribute("Description",questionById.getDescription());
        model.addAttribute("tag",questionById.getTag());
        model.addAttribute("id",questionById.getId());
        System.out.println("id"+id);
        return "publish";
    }

}
