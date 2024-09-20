package com.bnb.config;

import com.bnb.entity.AppUser;
import com.bnb.repositery.AppUserRepository;
import com.bnb.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;


@Component
public class JwtFilter extends OncePerRequestFilter {

    JwtService jwtService;

    AppUserRepository appUserRepositorytory;




    public JwtFilter(AppUserRepository appUserRepositorytory,JwtService jwtService) {
        this.appUserRepositorytory = appUserRepositorytory;
             this.jwtService=jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authentication = request.getHeader("Authorization");
              if (authentication!=null){
                  String token = authentication.substring(8, authentication.length() - 1);
                  System.out.println(token);
                  System.out.println(jwtService);
                  String userName = jwtService.getUserName(token);


                  Optional<AppUser> byUsername = appUserRepositorytory.findByUsername(userName);
                  if (byUsername.isPresent()){
                      AppUser appUser = byUsername.get();
                      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));
                      authenticationToken.setDetails(new WebAuthenticationDetails(request));
                      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                  }

    }
        filterChain.doFilter(request,response);
}}
