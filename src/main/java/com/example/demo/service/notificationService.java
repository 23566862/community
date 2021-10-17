package com.example.demo.service;

import com.example.demo.mapper.notificationMapper;
import com.example.demo.pojo.notification;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class notificationService {

    @Autowired
    private notificationMapper notificationMapper;

    public int addNotification(notification notification){
        int i = notificationMapper.addNotification(notification);
        return i;
    }

    public int selectCountByStatus( int status, int notifier){
        int count = notificationMapper.selectCountByStatus(status, notifier);
        return count;
    }

    public List<notification> getListById(int id,int status){
        List<notification> listById = notificationMapper.getListById(id, status);
        return listById;
    }


    public int updateStatus(int id){
        int i = notificationMapper.updateStatus(id);
        return i;
    }


}
