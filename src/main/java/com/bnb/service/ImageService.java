package com.bnb.service;

import com.bnb.entity.Image;
import com.bnb.entity.Property;

import com.bnb.repositery.ImageRepository;
import com.bnb.repositery.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {


    private PropertyRepository propertyRepository;
      private BucketService bucketService;
      private ImageRepository imageRepository;

    public ImageService(PropertyRepository propertyRepository, BucketService bucketService, ImageRepository imageRepository) {
        this.propertyRepository = propertyRepository;
        this.bucketService = bucketService;
        this.imageRepository = imageRepository;
    }




    public Image savedImage(MultipartFile multipartFile
    ,long property_id,
     String bucketName
    )                     throws Exception
    {

        Image image =new Image();
        Property property = propertyRepository.findById(property_id).get();
        String uri = bucketService.uploadImage(multipartFile, bucketName);
        image.setProperty(property);
        image.setUrl(uri);
        Image image1 = imageRepository.save(image);


        return null;
    }
}
