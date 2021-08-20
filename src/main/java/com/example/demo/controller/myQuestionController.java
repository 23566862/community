package com.example.demo.controller;

import com.example.demo.dto.pagInitDTO;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.user;
import com.example.demo.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class myQuestionController {

    @Autowired
    private userMapper mapper;
    @Autowired
    private questionService questionService;

    @RequestMapping("/question/{action}")
    public String question(HttpServletRequest request,
                           @PathVariable(name = "action") String action,
                           Model model,
                           @RequestParam(value = "pag",defaultValue = "1") Integer pag,
                           @RequestParam(value = "size",defaultValue = "5") Integer size){
        user user=null;
        Cookie[] cookies = request.getCookies();
        if (cookies !=null && cookies.length !=0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")){
                    String name = cookie.getValue();
                     user = mapper.finByNameRsUser(name);
                    if (user !=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

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
}
