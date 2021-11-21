package com.orsami.anime.anime.converter;

import org.springframework.stereotype.Component;

import com.orsami.anime.anime.Anime;
import com.orsami.anime.anime.web.AnimeView;
import com.orsami.anime.character.Character;
import com.orsami.anime.character.converter.CharacterToCharacterViewConverter;
import com.orsami.anime.character.web.CharacterView;
import com.orsami.anime.review.Review;
import com.orsami.anime.review.converter.ReviewToReviewViewConverter;
import com.orsami.anime.review.web.ReviewView;
import com.orsami.anime.staff.Staff;
import com.orsami.anime.staff.converter.StaffToStaffViewConverter;
import com.orsami.anime.staff.web.StaffView;
import com.orsami.anime.studio.converter.StudioToStudioViewConverter;

import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

@Component
public class AnimeToAnimeViewConverter implements Converter<Anime,AnimeView> {
	
	private final StudioToStudioViewConverter studioToStudioConverter;
	private final StaffToStaffViewConverter staffToStaffViewConverter;
	private final CharacterToCharacterViewConverter characterToCharacterViewConverter;
	private final ReviewToReviewViewConverter reviewToReviewViewConverter;
	
	public AnimeToAnimeViewConverter(StudioToStudioViewConverter studioToStudioConverter,
			StaffToStaffViewConverter staffToStaffViewConverter,
			CharacterToCharacterViewConverter characterToCharacterViewConverter,
			ReviewToReviewViewConverter reviewToReviewViewConverter) {
		this.studioToStudioConverter = studioToStudioConverter;
		this.staffToStaffViewConverter = staffToStaffViewConverter;
		this.characterToCharacterViewConverter = characterToCharacterViewConverter;
		this.reviewToReviewViewConverter = reviewToReviewViewConverter;
	}

	@Override
	public AnimeView convert(@NotNull Anime source) {
		AnimeView view = new AnimeView();
		view.setId(source.getId());
		view.setTags(source.getTags());
		view.setTitle(source.getTitle());
		view.setDescription(source.getDescription());
		view.setYears(source.getYears());
		view.setEpisode(source.getEpisode());
		view.setType(source.getType());
		view.setStudio(studioToStudioConverter.convert(source.getStudio()));
		
		Set<StaffView> staffviews= new HashSet<>();
		Set<Staff> staffs = source.getStaffs();
		staffs.forEach(staff -> {
			StaffView staffView = staffToStaffViewConverter.convert(staff);
			staffviews.add(staffView);
		});
		
		view.setStaffs(staffviews);
		Set<CharacterView> characterViews = new HashSet<>();
		source.getCharacters().forEach(character ->{
			CharacterView characterView = characterToCharacterViewConverter.convert(character);
			characterViews.add(characterView);
		});
		view.setCharacters(characterViews);
		
		Set<ReviewView> reviewViews = new HashSet<>();
		Set<Review> reviews = source.getReview();
		reviews.forEach(review->{
			ReviewView reviewView = reviewToReviewViewConverter.convert(review);
			reviewViews.add(reviewView);
		});
		view.setReviews(reviewViews);
		return view;
	}
	
	
	
	
	

}
