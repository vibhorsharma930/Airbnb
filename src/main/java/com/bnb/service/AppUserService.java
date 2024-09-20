package com.bnb.service;

import com.bnb.dto.AppUserDto;
import com.bnb.dto.AppUserLoginDto;
import com.bnb.entity.AppUser;

public interface AppUserService {

    AppUserDto createUser(AppUserDto appUserDto);

  AppUser verifyAppUser(AppUserLoginDto loginDto) ;


}
