package com.orsami.anime.review.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.orsami.anime.review.Review;
import com.orsami.anime.review.web.ReviewView;

@Component
public class ReviewToReviewViewConverter implements Converter<Review, ReviewView>{

	@Override
	public ReviewView convert(Review source) {
		ReviewView view = new ReviewView();
		view.setId(source.getId());
		view.setDescription(source.getDescription());
		view.setUser(source.getUser());
		view.setStar(source.getStars());
		return view;
	}
	
	

}
