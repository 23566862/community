package com.example.demo;

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
    userMapper mapper;
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        mapper.finByToken("30997d93-46cc-4067-810d-acf6788c309f");
    }

}
