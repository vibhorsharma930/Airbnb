package com.bnb.service;

import com.bnb.entity.City;
import com.bnb.entity.Country;
import com.bnb.entity.Property;
import com.bnb.repositery.CityRepository;
import com.bnb.repositery.CountryRepository;
import com.bnb.repositery.PropertyRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

  private   PropertyRepository propertyRepository;
  private CityRepository cityRepository;
  private CountryRepository countryRepository;


    public PropertyService(PropertyRepository propertyRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    public Property add(long city_id,long country_id,Property property){
        City city = cityRepository.findById(city_id).get();
        Country country = countryRepository.findById(country_id).get();

        property.setCity(city);
        property.setCountry(country);


        return propertyRepository.save(property);
    }

    public List<Property> getProperty(String location){

        return propertyRepository.findPropertyByLocation(location);
    }

  @Transactional
    public String delete(long propertyId) {
        try {
            propertyRepository.deleteById(propertyId);
            return "Property deleted successfully";
        } catch (EmptyResultDataAccessException e) {
            return "Property not found";
        } catch (DataIntegrityViolationException e) {
            // This is where SQLIntegrityConstraintViolationException will be handled
            return "Cannot delete property as it is referenced by other entities";
        }
    }

}

