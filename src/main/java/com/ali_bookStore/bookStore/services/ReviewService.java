package com.ali_bookStore.bookStore.services;

import com.ali_bookStore.bookStore.models.Book;
import com.ali_bookStore.bookStore.models.Review;
import com.ali_bookStore.bookStore.repositories.ReviewRepository;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
private final ReviewRepository reviewRepository;
private final MongoTemplate mongoTemplate;

    public ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate) {
        this.reviewRepository = reviewRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }
    public Review createReviewByBook(String reviewBody, String id){
        Review review = new Review(reviewBody);
        reviewRepository.insert(review);
        mongoTemplate.update(Book.class).matching(Criteria.where("_id").is(id)).apply(new Update().push("reviewIds").value(review)).first();
        return review;
    }
}
