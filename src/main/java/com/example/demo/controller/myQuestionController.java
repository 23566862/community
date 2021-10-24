package com.example.demo.controller;

import com.example.demo.dto.notificationDTO;
import com.example.demo.dto.pagInitDTO;
import com.example.demo.dto.questionDTO;
import com.example.demo.mapper.commentMapper;
import com.example.demo.mapper.questionMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.comment;
import com.example.demo.pojo.notification;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import com.example.demo.service.commentService;
import com.example.demo.service.notificationService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class myQuestionController {
    @Autowired
    private userMapper mapper;
    @Autowired
    private com.example.demo.mapper.questionMapper questionMapper;
    @Autowired
    private commentService commentService;
    @Autowired
    private questionService questionService;
    @Autowired
    private notificationService notificationService;
    /*我的问题页面展示*/
    @RequestMapping("/question/{action}")
    public String AllQuestion(HttpServletRequest request,
                           @PathVariable(name = "action") String action,
                           Model model,
                           @RequestParam(value = "pag",defaultValue = "1") Integer pag,
                           @RequestParam(value = "size",defaultValue = "5") Integer size){
        user user=null;
        user = (user) request.getSession().getAttribute("user");

        //判断前端的选择
        if ("question".equals(action)){
            model.addAttribute("select","question");
            model.addAttribute("selectName","我的提问");
        }

        if ("reply".equals(action)){
            model.addAttribute("select","reply");
            model.addAttribute("selectName","我的回复");
            //1 根据当前登入 id查出当前回复表 未读状态的数据
            List<notification> listById = notificationService.getListById(user.getId(), 0);
            if (listById.size() >0){
                for (notification notification : listById) {
                    if (notification.getType() ==1){
                        model.addAttribute("type","评论了问题");
                    }else if (notification.getType() ==2){
                        model.addAttribute("type","回复问题");
                    }
                }
                System.out.println(listById);
                ArrayList<Integer> outeridList = new ArrayList<>();
                ArrayList<Integer> notifierList = new ArrayList<>();
                for (notification notification : listById) {
                    outeridList.add(notification.getOuterid());
                    notifierList.add(notification.getNotifier());
                }
                List<notificationDTO> notificationDTOList = new ArrayList<>();
                System.out.println("outeridList"+outeridList);
                System.out.println("notifierList"+notifierList);
                //2查询question表的数据 foreach查询 ，条件为 1集合的outerid

                List<question> question = new ArrayList<>();
                for (int i = 0; i <outeridList.size() ; i++) {
                    question.add(questionMapper.getQuestionById(outeridList.get(i))) ;
                }
                System.out.println("question"+question);

                //3 查询user表的数据foreach查询 ，条件为 1集合的notifier
                ArrayList<user> userForeach = new ArrayList<>();
                for (int i = 0; i <notifierList.size() ; i++) {
                    userForeach.add(mapper.finById(notifierList.get(i)));
                }
                for (int i = 0; i <question.size() ; i++) {
                    notificationDTO notificationDTO1 = new notificationDTO();
                    notificationDTO1.setQuestion(question.get(i));
                    notificationDTO1.setUser(userForeach.get(i));
                    notificationDTO1.setNotification(listById.get(i));
                    notificationDTOList.add(notificationDTO1);
                }

                System.out.println("notificationDTOList"+notificationDTOList);
                model.addAttribute("notificationDTOList",notificationDTOList);
            }


        }
        //遍历的问题和回复总数
        int allCountById = questionMapper.getAllCountById(user.getId());
        model.addAttribute("questionCount",allCountById);
        int replyCount = notificationService.selectCountByStatus(0, user.getId());
        model.addAttribute("replyCount",replyCount);
        //便利的数据
        pagInitDTO pagInitDTO = questionService.getlistByCreator(user.getId(), pag, size);

        System.out.println(pagInitDTO);
        model.addAttribute("questionList",pagInitDTO);
        return "myQuestion";
    }


    /*问题详情页*/
    @RequestMapping("/UseQuestion/{id}")
    public String UseQuestion(@PathVariable(name = "id") int id, Model model){

        //阅读数+1
        questionService.incViewCount(id);
        questionDTO questionById = questionService.getQuestionById(id);
        //进入问题就修改未读状态
        notificationService.updateStatus(id);
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
