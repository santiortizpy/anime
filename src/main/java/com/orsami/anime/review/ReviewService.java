package com.orsami.anime.review;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orsami.anime.error.EntityNotFoundException;
import com.orsami.anime.review.converter.ReviewToReviewViewConverter;
import com.orsami.anime.review.web.ReviewBaseReq;
import com.orsami.anime.review.web.ReviewView;
import com.orsami.anime.util.MessageUtil;

@Service
public class ReviewService {
	private final ReviewRepo repo;
	private final ReviewToReviewViewConverter converter;
	private final MessageUtil messageUtil;
	
	
	
	public ReviewService(ReviewRepo repo, ReviewToReviewViewConverter converter, MessageUtil messageutil) {
		super();
		this.repo = repo;
		this.converter = converter;
		this.messageUtil = messageutil;
	}
	
	public Review findReviewOrThrow(Long id) {
		return repo.findById(id).orElseThrow(()-> new EntityNotFoundException(messageUtil.getMessage("review.NotFound",id)));
	}
	
	public ReviewView getReview(Long id) {
		Review review = findReviewOrThrow(id);
		return converter.convert(review);
	}
	
	public Page<ReviewView> findAllReviews(Pageable pageable){
		Page<Review> reviews = repo.findAll(pageable);
		List<ReviewView> reviewViews = new ArrayList<>();
		reviews.forEach(review -> {
			ReviewView reviewView = converter.convert(review);
			reviewViews.add(reviewView);
		});
		return new PageImpl<>(reviewViews,pageable, reviews.getTotalElements());
	}
	
	public ReviewView createReview(ReviewBaseReq req) {
		Review review = new Review();
		this.prepare(review, req);
		Review reviewSave = repo.save(review);
		return converter.convert(reviewSave);
	}
	
	public ReviewView updateReview(Review reviewView,ReviewBaseReq req) {
		Review review = this.prepare(reviewView, req);
		Review reviewSave = repo.save(review);
		return converter.convert(reviewSave);
	}
	
	@Transactional
	public void deleteReview(Long id) {
		try {
			repo.deleteById(id);
			
		}catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(messageUtil.getMessage("review.NotFound",id));
				}
	}



	public Review prepare(Review review, ReviewBaseReq req) {
		review.setDescription(req.getDescription());
		review.setUser(req.getUser());
		review.setStars(req.getStar());
		return review;
	}

}
