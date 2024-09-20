package com.bnb.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AWSS3Config {
    @Value("${aws.accessKey}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretKey;
    @Value("${aws.region}")
    private String reigon;


    public AWSCredentials awsCredentials() {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return credentials;
    }

    @Bean
    public AmazonS3 amazonS3() {
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials("accessKey", "secretKey")))
                .withRegion(reigon).build();


        return s3client;
    }
}

//@Bean for sdk 2.x.x
//public S3Client s3Client() {
//    return S3Client.builder()
//            .credentialsProvider(StaticCredentialsProvider.create(
//                    AwsBasicCredentials.create("your-access-key", "your-secret-key")))
//            .region(Region.reigon) // e.g., Region.US_WEST_2
//            .build();






