package com.orsami.anime.review.web;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orsami.anime.review.Review;
import com.orsami.anime.review.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	private final ReviewService service;
	
	public ReviewController(ReviewService service) {
		this.service=service;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ReviewView getReview(@PathVariable Long id) {
		return service.getReview(id);
	}
	
	@GetMapping
	@ResponseBody
	public Page<ReviewView> getAllReviews(@PageableDefault(sort="id", direction= Sort.Direction.ASC)Pageable pageable){
		return service.findAllReviews(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ReviewView createReview(@RequestBody @Valid ReviewBaseReq req) {
		return service.createReview(req);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ReviewView updateReview(@PathVariable(name="id") Long id, 
			@RequestBody @Valid ReviewBaseReq req) {
		Review review = service.findReviewOrThrow(id);
		return service.updateReview(review, req);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReview(@PathVariable Long id) {
		service.deleteReview(id);
	}
	

}
