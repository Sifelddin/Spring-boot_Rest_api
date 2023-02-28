package com.ali_bookStore.bookStore.controllers;
import com.ali_bookStore.bookStore.models.Review;
import com.ali_bookStore.bookStore.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController  {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> allReviews(){
        return new ResponseEntity<List<Review>>(reviewService.getAllReviews(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String,String> payload){
        return new ResponseEntity<Review>(reviewService.createReviewByBook(payload.get("reviewBody"),payload.get("bookId")), HttpStatus.CREATED);
        }
}
