package com.example.demo;

import com.example.demo.mapper.questionMapper;
import com.example.demo.mapper.userMapper;
import com.example.demo.pojo.user;
//import com.example.demo.mapper.userMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    questionMapper questionMapper;
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        int allCountById = questionMapper.getAllCountById(10);
        System.out.println(allCountById);

    }

}
