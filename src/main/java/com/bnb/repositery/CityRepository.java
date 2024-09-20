package com.bnb.repositery;

import com.bnb.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestResource(path = "cities")
public interface CityRepository extends JpaRepository<City, Long> {
}
