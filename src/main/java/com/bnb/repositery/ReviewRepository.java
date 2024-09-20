package com.bnb.repositery;

import com.bnb.entity.AppUser;
import com.bnb.entity.Property;
import com.bnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(" Select r from Review r WHERE r.property=:property And r.appUser=:appUser")
    Review findByPropertyAndAppUser(@Param("property")Property property, @Param("appUser")AppUser appUser);


}