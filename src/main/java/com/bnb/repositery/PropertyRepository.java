package com.bnb.repositery;

import com.bnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("select p from Property p JOIN City c on p.city=c.id JOIN Country co on p.country=co.id WHERE c.city=:em OR co.country=:em ")
    List<Property> findPropertyByLocation(@Param("em") String location);
}

