package com.example.demo.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import org.springframework.beans.factory.annotation.Value;

public class OssUtil {

     public static  String aliyunID="LTAI5t7ZGqncog477otkY2kv"; //id
     public static  String aliyunKey="PafRKpY2mbwbXaZ5dJvniXQBSzH76X"; //阿里key
   public static  String bucketUrl="uploadimgs1.oss-cn-beijing.aliyuncs.com"; //仓库url
     public static  String bucketName="uploadimgs1"; //仓库名
     public static  String endpointUrl="oss-cn-beijing.aliyuncs.com" ; //域名节点
    public static OSS getOssClient(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpointUrl, aliyunID, aliyunKey);
        if (!ossClient.doesBucketExist(bucketName)){
         //如果仓库不存在就创建
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(null);
            createBucketRequest.setBucketName(bucketName);
            //只能读
            createBucketRequest.setCannedACL( CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        return ossClient;
    }

}
