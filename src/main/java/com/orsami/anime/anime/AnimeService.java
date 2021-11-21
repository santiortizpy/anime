package com.orsami.anime.anime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orsami.anime.anime.converter.AnimeToAnimeViewConverter;
import com.orsami.anime.anime.web.AnimeBaseReq;
import com.orsami.anime.anime.web.AnimeView;
import com.orsami.anime.base.BaseRequest;
import com.orsami.anime.character.Character;
import com.orsami.anime.character.CharacterRepo;
import com.orsami.anime.error.EntityNotFoundException;
import com.orsami.anime.review.Review;
import com.orsami.anime.review.ReviewRepo;
import com.orsami.anime.staff.Staff;
import com.orsami.anime.staff.StaffRepo;
import com.orsami.anime.studio.StudioRepo;
import com.orsami.anime.util.MessageUtil;

@Service
public class AnimeService {

	private final AnimeRepo repo;
	private final AnimeToAnimeViewConverter converter;
	private final StudioRepo studioRepo;
	private final StaffRepo staffRepo;
	private final ReviewRepo reviewRepo;
	private final CharacterRepo characterRepo;
	private final MessageUtil messageUtil;
	public AnimeService(AnimeRepo repo, AnimeToAnimeViewConverter converter, StudioRepo studioRepo,
			StaffRepo staffRepo, ReviewRepo reviewRepo, CharacterRepo characterRepo, MessageUtil messageUtil) {
		this.repo = repo;
		this.converter = converter;
		this.studioRepo = studioRepo;
		this.staffRepo = staffRepo;
		this.reviewRepo = reviewRepo;
		this.characterRepo = characterRepo;
		this.messageUtil = messageUtil;
	}
	
	public Anime findAnimeorThrow(Long id) {
		return repo.findById(id)
				.orElseThrow(()-> new EntityNotFoundException(messageUtil.getMessage("anime.NotFound", id)));
	}
	
	public AnimeView getAnime (Long id) {
		Anime anime = findAnimeorThrow(id);
		return converter.convert(anime);
	}
	
	public Page<AnimeView> findAllAnimes(Pageable pageable){
		Page<Anime> animes = repo.findAll(pageable);
		List<AnimeView> animeViews = new ArrayList<>();
		animes.forEach(anime->{
			AnimeView animeView = converter.convert(anime);
			animeViews.add(animeView);
		});
		return new PageImpl<>(animeViews, pageable, animes.getTotalElements());
	}
	
	public AnimeView createAnime(AnimeBaseReq req) {
		Anime anime = new Anime();
		this.prepare(anime, req);
		Anime animeSave = repo.save(anime);
		return converter.convert(animeSave);
	}
	public AnimeView updateAnime(Anime anime, AnimeBaseReq req) {
		Anime animeNew = this.prepare(anime, req);
		Anime animeSave = repo.save(animeNew);
		return converter.convert(animeSave);
	}
	 @Transactional
	 public void deleteAnime(Long id) {
		 try {
			 repo.deleteById(id);
		 }catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(messageUtil.getMessage("anime.NotFound",id));
		}
	 }
	
	private Anime prepare(Anime anime, AnimeBaseReq req) {
		anime.setTitle(req.getTitle());
		anime.setDescription(req.getDescription());
		anime.setYears(req.getYears());
		anime.setType(req.getType());
		anime.setEpisode(req.getEpisode());
		anime.setTags(req.getTags());
		anime.setStudio(studioRepo.getOne(req.getStudioId()));
		
		List<Staff> staffList = staffRepo.findAllById(req.getStaffs()
				.stream()
				.map(BaseRequest.Id::getId)
				.collect(Collectors.toSet()));
		Set<Staff> staffs = new HashSet<>(staffList);
		anime.setStaffs(staffs);
		
		List<Character> characterList = characterRepo.findAllById(req.getCharacters()
				.stream()
				.map(BaseRequest.Id::getId)
				.collect(Collectors.toSet()));
		Set<Character> characters = new HashSet<>(characterList);
		anime.setCharacters(characters);
		
		List<Review> reviewList = reviewRepo.findAllById(req.getReviews()
				.stream()
				.map(BaseRequest.Id::getId)
				.collect(Collectors.toSet()));
		Set<Review> reviews = new HashSet<>(reviewList);
		anime.setReview(reviews);
		return anime;
		
	}
}
