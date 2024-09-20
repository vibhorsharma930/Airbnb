package com.bnb.repositery;

import com.bnb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "country")
public interface CountryRepository extends JpaRepository<Country, Long> {
}