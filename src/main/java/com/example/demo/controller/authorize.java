package com.example.demo.controller;

import com.example.demo.dto.GitHubUser;
import com.example.demo.dto.accessToken;
import com.example.demo.pojo.user;
import com.example.demo.provider.GitHubProvider;
import com.example.demo.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class authorize {
    @Autowired
    GitHubProvider gitHubProvider;
    /*读取配置文件里的value*/
    @Value("${github.setClient_id}")
    private String setClient_id;
    @Value("${github.setClient_secret}")
    private String setClient_secret;
    @Value("${github.setRedirect_uri}")
    private String setRedirect_uri;

    @Autowired
   private userMapper userMapper;

    /*等会回调的callback请求
    * 获取accessToken获取用户的user*/
    @GetMapping("/callback")
    public String getCode(@RequestParam(name = "code") String code,
                          @RequestParam(name = "state")String state,
                            HttpServletRequest request,
                            HttpServletResponse response){
        accessToken accessToken = new accessToken();
        accessToken.setClient_id(setClient_id);
        accessToken.setClient_secret(setClient_secret);
        accessToken.setRedirect_uri(setRedirect_uri);
        accessToken.setCode(code);
        accessToken.setState(state);
        String accessToken1 = gitHubProvider.getAccessToken(accessToken);
        GitHubUser gitHubUser = gitHubProvider.getGitHubUser(accessToken1);
        if (gitHubUser !=null && gitHubUser.getLogin() !=null){
            //用户值存进数据库
            String token = UUID.randomUUID().toString();
            user user = new user(String.valueOf(gitHubUser.getId()),gitHubUser.getLogin(),token,System.currentTimeMillis(),System.currentTimeMillis(),gitHubUser.getAvatar_url());
            System.out.println("++++"+user.toString());
            //如果当前用户在第一次登入就在数据库插入数据
            if (!StringUtils.isEmpty(userMapper.finByName(user.getName()))){
                //存放进session
                request.getSession().setAttribute("user",gitHubUser);
                //把token存进cookie中
                System.out.println("user.getId()"+user.getName());
                response.addCookie(new Cookie("name",user.getName()));
                return "index";
            }else{
                userMapper.addUser(user);
            }

        }else{
            return "index";
        }

        return "error";
    }
}
