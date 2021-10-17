package com.example.demo;

//import com.example.demo.mapper.userMapper;
import com.example.demo.dto.commentDTO;
import com.example.demo.mapper.commentMapper;
import com.example.demo.mapper.notificationMapper;
import com.example.demo.pojo.comment;
import com.example.demo.pojo.notification;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

        import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    commentMapper commentMapper;
    @Autowired
    private notificationMapper notificationMapper;
    @Test
    void contextLoads() throws SQLException {


    }

    @Test
    void text(){
        System.out.println( notificationMapper.selectCountByStatus(1, 14)
        );
    }


}
