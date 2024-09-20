package com.bnb.controller;

import com.bnb.entity.Image;
import com.bnb.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/api/v1/image")
public class ImageController {

   private ImageService imageService;

    public ImageController(ImageService imageService) {

        this.imageService = imageService;
    }

    @PostMapping("/upload/{bucketName}/property")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile multipartFile
                                         , @PathVariable  String bucketName
                                         ,@RequestParam long property_id
    )  {

        Image savedmsg = null;
        try {
            savedmsg = imageService.savedImage(multipartFile, property_id, bucketName);
        } catch (Exception e) {
            log.error("id password is wrong ");            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(savedmsg, HttpStatus.OK);

    }
}
