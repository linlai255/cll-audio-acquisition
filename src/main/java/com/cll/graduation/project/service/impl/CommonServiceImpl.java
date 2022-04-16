package com.cll.graduation.project.service.impl;

import com.cll.graduation.project.service.CommonService;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    private volatile MinioClient minioClient;



    @Value("${app.minio.endpoint}")
    private String endpoint;

    @Value("${app.minio.accessKey}")
    private String accessKey;

    @Value("${app.minio.secretKey}")
    private String secretKey;

    @Value("${app.minio.bucketName}")
    private String bucketName;



    private MinioClient getInstance(){
        if (minioClient == null) {
            synchronized (MinioClient.class) {
                if (minioClient == null) {
                    try {
                        minioClient = new MinioClient(endpoint, accessKey, secretKey);
                    } catch (InvalidEndpointException e) {
                        log.error("new MinioClient error: {}", e);
                        throw new IllegalArgumentException("实列化minio失败");
                    } catch (InvalidPortException e) {
                        log.error("new MinioClient error: {}", e);
                        throw new IllegalArgumentException("实列化minio失败");
                    }
                }
            }
        }
        return minioClient;
    }




    @Override
    public String uploadFile(MultipartFile file) {
        ByteArrayInputStream byteArrayInputStream;
        Integer size;
        try {
            byte[] bytes = file.getBytes();
            size = bytes.length;
            byteArrayInputStream = new ByteArrayInputStream(bytes);
        } catch (IOException e) {
            log.error("file error: {}", e);
            throw new IllegalArgumentException("文件转换异常");
        }
        try {
            this.getInstance().putObject(bucketName, "test.txt", byteArrayInputStream, byteArrayInputStream.available(), "application/octet-stream");
        } catch (InvalidBucketNameException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException("不合法的存储桶名称");
        } catch (NoSuchAlgorithmException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException("找不到相应的签名算法");
        } catch (IOException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException("连接异常");
        } catch (InvalidKeyException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException("不合法的access key或者secret key");
        } catch (NoResponseException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException(" 服务器无响应");
        } catch (XmlPullParserException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException("解析返回的XML异常");
        } catch (ErrorResponseException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException("执行失败异常");
        } catch (InternalException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException("内部错误");
        } catch (InvalidArgumentException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException("上传失败");
        } catch (InsufficientDataException e) {
            log.error("upload file error: {}", e);
            throw new IllegalArgumentException("在读到相应length之前就得到一个EOFException");
        }
        return null;
    }
}
