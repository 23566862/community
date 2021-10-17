package com.example.demo.mapper;

import com.example.demo.pojo.notification;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface notificationMapper {
    int addNotification(notification notification);
    int selectCountByStatus(@Param("status") int status,@Param("notifier") int notifier);
    List<notification> getListById(@Param("id")int id,@Param("status")int status);
    int updateStatus(@Param("id")int outerid);
}
