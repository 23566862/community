package com.example.demo.service;

import com.example.demo.dto.pagInitDTO;
import com.example.demo.dto.questionDTO;
import com.example.demo.dto.tagDTO;
import com.example.demo.exception.errorCode;
import com.example.demo.exception.statusException;
import com.example.demo.mapper.commentMapper;
import com.example.demo.mapper.questionMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.comment;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class questionService {
    @Autowired
    private userMapper mapper;
    @Autowired
    private questionMapper questionMapper;
    @Autowired
    private commentMapper commentMapper;
    public pagInitDTO getList(int pag,int size) {
        //pag 是当前页面所在下标
       int  start=size*(pag-1);
        List<question> list = questionMapper.getList(start, size);
        ArrayList<questionDTO>  questionDTOList = new ArrayList<>();
        pagInitDTO pagInitDTO = new pagInitDTO();
        for (question question : list) {
            user user = mapper.finById(question.getCreator());
            questionDTO questionDTO = new questionDTO();
            //快速给questionDTO赋值
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagInitDTO.setQuestionDTO(questionDTOList);
        int allCount = questionMapper.getAllCount();
        pagInitDTO.setParmInit(allCount,pag,size);
        return pagInitDTO;
    }

    public pagInitDTO getlistByCreator(int id, Integer pag, Integer size) {
        //pag 是当前页面所在下标
        int  start=size*(pag-1);
        pagInitDTO pagInitDTO = new pagInitDTO();
        List<question> listByCreator = questionMapper.getListByCreator(id, start, size);
        System.out.println(listByCreator);
        ArrayList<questionDTO>  questionDTOList = new ArrayList<>();
        for (question question : listByCreator) {
            user user = mapper.finById(question.getCreator());
            questionDTO questionDTO = new questionDTO();
            //快速给questionDTO赋值
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagInitDTO.setQuestionDTO(questionDTOList);
        int allCount = questionMapper.getAllCountById(id);
        pagInitDTO.setParmInit(allCount,pag,size);
        return pagInitDTO;

    }

    public questionDTO getQuestionById(int id) {
        question question = questionMapper.getQuestionById(id);
        user user = mapper.finById(question.getCreator());
        if (user ==null){
            throw new statusException(errorCode.QUESTION_NOT_FOUND);
        }
        questionDTO questionDTO = new questionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void updateOrCreate(question question){
        if (question.getId() ==0){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.addQuestion(question);
        }else{
            question.setGmtModified(System.currentTimeMillis());
            int rest = questionMapper.updateQuestion(question);
        }
    }

    //阅读数+1
    public void incViewCount(int id) {
        question question = questionMapper.getQuestionById(id);
        questionMapper.updateQuestion(question);

    }

    public List<comment> getCommentList(int id,int type){
        List<comment> comment = commentMapper.getComment(id,type);
        return comment;
    }

    public List<question> getWhereByTag(question question){
        List<question> whereByTag = questionMapper.getWhereByTag(question);
        return whereByTag;
    }

    //添加标签
    public List<tagDTO> getListTag(){
        ArrayList<tagDTO> tagList = new ArrayList<>();
        tagDTO tag = new tagDTO();
        tag.setName("开发语言");
        tag.setList(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagList.add(tag);

        tagDTO tag1 = new tagDTO();
        tag1.setName("开发框架");
        tag1.setList(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagList.add(tag1);

        return tagList;

    }
}
