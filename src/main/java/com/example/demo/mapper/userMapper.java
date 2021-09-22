package com.example.demo.mapper;

import com.example.demo.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface userMapper {
    int addUser(user user);
    user finByToken(@Param("token") String token);
    user finById(@Param("id") int id);
    String finByName(@Param("name") String name);
    user finByNameRsUser(@Param("name") String name);
}
