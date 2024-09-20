package com.bnb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/v1/dummy")
public class DummyController {
    @PostMapping
    public ResponseEntity<?> dummyResponse( ){
        return  new ResponseEntity<>("dummy message", HttpStatus.OK);
    }
}
