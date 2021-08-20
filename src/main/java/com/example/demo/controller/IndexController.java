package com.example.demo.controller;

/*import com.example.demo.mapper.userMapper;*/
import com.example.demo.dto.pagInitDTO;
import com.example.demo.dto.questionDTO;
import com.example.demo.mapper.questionMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import com.example.demo.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private userMapper mapper;
    @Autowired
    private questionService questionService;
    @RequestMapping("/")
    //首页判断携带的cookie是否在数据库中存在
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "pag",defaultValue = "1") Integer pag,
                        @RequestParam(value = "size",defaultValue = "5") Integer size){
        Cookie[] cookies = request.getCookies();
        if (cookies !=null && cookies.length !=0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user user = mapper.finByToken(token);
                    if (user !=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        pagInitDTO pagInitDTO = questionService.getList(pag, size);

        model.addAttribute("questionList",pagInitDTO);
        return "index";
    }


}
