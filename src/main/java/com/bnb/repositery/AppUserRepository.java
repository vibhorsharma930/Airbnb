package com.bnb.repositery;

import com.bnb.dto.AppUserDto;
import com.bnb.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {


        Optional<AppUser> findByUsernameAndEmail(String username,String email);
        Optional<AppUser> findByUsername(String username);

}