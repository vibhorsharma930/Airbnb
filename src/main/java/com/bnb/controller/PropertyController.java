package com.bnb.controller;


import com.bnb.entity.Property;
import com.bnb.service.PropertyService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {


    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @PostMapping("/{city_id}/property")
    public ResponseEntity<?> addProperty(@PathVariable long city_id,
                                         @RequestParam long country_id,
                                         @RequestBody Property property
    ) {

        Property property1 = propertyService.add(city_id, country_id, property);

        return new ResponseEntity<>(property1, HttpStatus.OK);

    }

    @GetMapping("/search_property")
    public ResponseEntity<?> getProperty(@RequestParam String location) {

        List<Property> propertys = propertyService.getProperty(location);


        return new ResponseEntity<>(propertys, HttpStatus.FOUND);

    }

    @DeleteMapping("/delete_property")
    @Transactional
    public ResponseEntity<String> deleteProperty(@RequestParam long property_id) {

        String deletedById = propertyService.delete(property_id);

        return new ResponseEntity<>(deletedById, HttpStatus.OK);

    }

}
