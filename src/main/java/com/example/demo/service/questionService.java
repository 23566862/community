package com.example.demo.service;

import com.example.demo.dto.pagInitDTO;
import com.example.demo.dto.questionDTO;
import com.example.demo.mapper.questionMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class questionService {
    @Autowired
    private userMapper mapper;
    @Autowired
    private questionMapper questionMapper;

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

        System.out.println(allCount);
        pagInitDTO.setParmInit(allCount,pag,size);

        return pagInitDTO;
    }

    public pagInitDTO getlistByCreator(int id, Integer pag, Integer size) {
        pagInitDTO pagInitDTO = new pagInitDTO();
        int allCount = questionMapper.getAllCountById(id);




        System.out.println("allcount:"+allCount);
        pagInitDTO.setParmInit(allCount,pag,size);
        List<question> listByCreator = questionMapper.getListByCreator(id, pag, size);
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
        System.out.println("id:"+id);

        System.out.println(pagInitDTO);
        return pagInitDTO;

    }
}
