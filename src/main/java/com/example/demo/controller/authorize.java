package com.example.demo.controller;

import com.example.demo.dao.GitHubUser;
import com.example.demo.dao.accessToken;
import com.example.demo.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public String getCode(@RequestParam(name = "code") String code,@RequestParam(name = "state")String state){
        accessToken accessToken = new accessToken();
        accessToken.setClient_id(setClient_id);

        accessToken.setClient_secret(setClient_secret);
        accessToken.setRedirect_uri(setRedirect_uri);
        accessToken.setCode(code);
        accessToken.setState(state);
        String accessToken1 = gitHubProvider.getAccessToken(accessToken);
        GitHubUser gitHubUser = gitHubProvider.getGitHubUser(accessToken1);
        return "index";
    }
}
