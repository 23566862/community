package com.example.demo.service;

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
    public List<questionDTO> getList() {
        List<question> list = questionMapper.getList();
        ArrayList< questionDTO>  questionDTOList = new ArrayList<>();
        for (question question : list) {
            user user = mapper.finById(question.getCreator());
            questionDTO questionDTO = new questionDTO();
            //快速给questionDTO赋值
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
