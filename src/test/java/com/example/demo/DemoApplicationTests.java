package com.example.demo;

//import com.example.demo.mapper.userMapper;
import com.example.demo.dto.commentDTO;
import com.example.demo.pojo.comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

        import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    com.example.demo.mapper.commentMapper commentMapper;
    @Test
    void contextLoads() throws SQLException {

        List<comment> comment = commentMapper.getComment(6, 1);
        System.out.println(comment);

    }

}
