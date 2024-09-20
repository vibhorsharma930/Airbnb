package com.bnb.dto;

import lombok.Data;

@Data
public class AppUserDto {

    private String password;
    private String email;
    private String username;
    private String mobile;
    private String name;

    private String role;


}
