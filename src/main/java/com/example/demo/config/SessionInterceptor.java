package com.example.demo.config;

import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.user;
import com.example.demo.service.notificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    userMapper mapper;
    @Autowired
    private notificationService notificationService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies !=null && cookies.length !=0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")){
                    String name = cookie.getValue();
                    user user = mapper.finByNameRsUser(name);
                    if (user !=null){
                        //查询未读通知条数
                        int count = notificationService.selectCountByStatus(0, user.getId());
                        request.getSession().setAttribute("user",user);
                        request.getSession().setAttribute("count",count);
                    }
                    break;
                }
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
