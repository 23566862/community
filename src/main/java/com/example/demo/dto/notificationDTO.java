package com.example.demo.dto;

import com.example.demo.pojo.notification;
import com.example.demo.pojo.question;
import com.example.demo.pojo.user;
import lombok.Data;

import java.util.List;

@Data
public class notificationDTO {
   private user user;
   private question question;
   private notification notification;
}
