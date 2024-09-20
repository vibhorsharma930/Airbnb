package com.bnb.service;

import com.bnb.entity.AppUser;
import com.bnb.entity.Property;
import com.bnb.entity.Review;
import com.bnb.repositery.PropertyRepository;
import com.bnb.repositery.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;

    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }


    public Review createReviewByUser(
            long property_id,
            Review review,
            AppUser appUser
    ){

        Optional<Property> byId = propertyRepository.findById(property_id);

        if (byId.isPresent()){

            Property property = byId.get();
            Review byPropertyAndAppUser = reviewRepository.findByPropertyAndAppUser(property, appUser);
            if (byPropertyAndAppUser!=null){
                return null;


            }

            review.setProperty(property);
            review.setAppUser(appUser);
            Review review1 = reviewRepository.save(review);
            return review1;

        }
        return null;

    }

    public void deleteReview(long review_id){
        reviewRepository.deleteById(review_id);
    }

}
