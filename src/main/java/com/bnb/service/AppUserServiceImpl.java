package com.bnb.service;

import com.bnb.dto.AppUserDto;
import com.bnb.dto.AppUserLoginDto;
import com.bnb.entity.AppUser;
import com.bnb.exception.UserExistException;
import com.bnb.repositery.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements  AppUserService{

      private AppUserRepository appUserRepository;



    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;

    }

    @Override
    public AppUserDto createUser(AppUserDto appUserDto) {


        Optional<AppUser> byUsername = appUserRepository.findByUsernameAndEmail(appUserDto.getUsername(), appUserDto.getEmail());
        if (byUsername.isPresent()){

            throw new UserExistException("this user already exist");

        }
        AppUser user=  this.mapToEntity(appUserDto);
        String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(7));

        user.setPassword(hashpw);
        AppUser user1 = appUserRepository.save(user);


        AppUserDto  appUserDto1=    this.mapToDto(user1);

        return  appUserDto1;
    }

    @Override
    public AppUser verifyAppUser(AppUserLoginDto loginDto) {


        Optional<AppUser> byUsername = appUserRepository.findByUsername(loginDto.getUsername());

        if (byUsername.isPresent()){
            AppUser user = byUsername.get();
            boolean checkpw = BCrypt.checkpw(loginDto.getPassword(), user.getPassword());
            if (checkpw==true){
                return  user;
            }
        }else {

            return null;
        }
        return null;
    }

    private AppUserDto mapToDto(AppUser user1) {

        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setEmail(user1.getEmail());
        appUserDto.setName(user1.getName());
        appUserDto.setMobile(user1.getMobile());
        appUserDto.setPassword(user1.getPassword());
        appUserDto.setUsername(user1.getUsername());
        appUserDto.setRole(user1.getRole());
       return appUserDto;
    }

    private AppUser mapToEntity(AppUserDto appUserDto) {

        AppUser user =new AppUser();
        user.setName(appUserDto.getName());
        user.setEmail(appUserDto.getEmail());
        user.setMobile(appUserDto.getMobile());
        user.setPassword(appUserDto.getPassword());
        user.setUsername(appUserDto.getUsername());
        user.setRole(appUserDto.getRole());
        return user;
    }


}

