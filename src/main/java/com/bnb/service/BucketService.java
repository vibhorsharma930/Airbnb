package com.bnb.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static java.lang.System.getProperty;

@Service
public class BucketService {

    @Autowired
    private AmazonS3 amazonS3;

    public String uploadImage(MultipartFile multipartFile,String bucketName) throws Exception {


        File file =new File(System.getProperty("java.io.tmpdir")+"/"+multipartFile.getOriginalFilename());


         FileOutputStream fileOutputStream = new FileOutputStream(file);

         fileOutputStream.write(multipartFile.getBytes());

         amazonS3.putObject(bucketName,multipartFile.getOriginalFilename(),file);

        return amazonS3.getUrl(bucketName,multipartFile.getOriginalFilename()).toString();



    }
}
