package com.bnb.controller;


import com.bnb.entity.AppUser;
import com.bnb.entity.Review;
import com.bnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create_review")
    public ResponseEntity<?> createReview(
            @RequestParam long property_id,
            @RequestBody Review review,
            @AuthenticationPrincipal AppUser appUser){

        System.out.println(appUser.getEmail());

        Review reviewByUser = reviewService.createReviewByUser(property_id, review, appUser);
          if (reviewByUser == null){

              return new ResponseEntity<>("review  already exist! you wanna update it", HttpStatus.FOUND);
          }

      return new ResponseEntity<>("review created ",HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public  ResponseEntity<?> deleteReviewById(@RequestParam long review_id){
        reviewService.deleteReview(review_id);
        return new ResponseEntity<>("review deleted",HttpStatus.OK);
    }
}
