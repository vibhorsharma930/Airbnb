package com.bnb.controller;

import com.bnb.dto.AppUserDto;
import com.bnb.dto.AppUserLoginDto;
import com.bnb.dto.JwtTokenDto;
import com.bnb.entity.AppUser;
import com.bnb.exception.UserExistException;
import com.bnb.service.AppUserServiceImpl;
import com.bnb.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bnb")
public class AppUserController {

    private AppUserServiceImpl appUserService;
     private  JwtService jwtService;

    public AppUserController(AppUserServiceImpl appUserService, JwtService jwtService) {
        this.appUserService = appUserService;
        this.jwtService = jwtService;
    }
    @PostMapping
    public ResponseEntity<AppUserDto> signUp(@RequestBody AppUserDto appUserDto){
          appUserDto.setRole("Role_User");
         AppUserDto appUserDto1 =appUserService.createUser(appUserDto);


        return new ResponseEntity<>(appUserDto1, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUserLoginDto loginDto){


        AppUser user = appUserService.verifyAppUser(loginDto);

        if(user!=null){
            String token= jwtService.generateToken(user);
            JwtTokenDto jwtTokenDto =new JwtTokenDto();
            jwtTokenDto.setTokenType("JWT");
            jwtTokenDto.setToken(token);
            return new ResponseEntity<>(jwtTokenDto,HttpStatus.OK);

        }else {

            throw new UserExistException("user does not exist / invalid password");

        }


    }


    @PostMapping("/signUp/manager")
    public ResponseEntity<AppUserDto> signUpManager(@RequestBody AppUserDto appUserDto){

        appUserDto.setRole("Role_Manager");
        AppUserDto appUserDto1 =appUserService.createUser(appUserDto);


        return new ResponseEntity<>(appUserDto1, HttpStatus.CREATED);
    }


}
