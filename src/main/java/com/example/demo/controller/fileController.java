package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.example.demo.dto.imgDTO;
import com.example.demo.util.OssUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
//文件上传图片
@Controller
public class fileController {
    @PostMapping("/file/upload")
    @ResponseBody
    public Object url(HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            //随机名处理
            String fileName =new Date().getTime() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            OSS ossClient = OssUtil.getOssClient();
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setContentEncoding("utf-8");
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(fileName.substring(fileName.lastIndexOf(".")));
            //上传文件
            ossClient.putObject(OssUtil.bucketName, fileName, inputStream, objectMetadata);
            // 设置URL过期时间为1小时。
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            String url = ossClient.generatePresignedUrl(OssUtil.bucketName, fileName, expiration).toString();
            ossClient.shutdown();
            hashMap.put("success",1);
            hashMap.put("url",url);
            hashMap.put("message","成功");
        } catch (IOException e) {
            hashMap.put("success",0);
            hashMap.put("message","图片上传失败");
        }







        return hashMap;
    }



}
