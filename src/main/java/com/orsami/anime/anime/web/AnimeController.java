package com.orsami.anime.anime.web;

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

import com.orsami.anime.anime.Anime;
import com.orsami.anime.anime.AnimeService;

@RestController
@RequestMapping("/animes")
public class AnimeController {
	
	private final AnimeService service;

	public AnimeController(AnimeService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public AnimeView getAnime(@PathVariable Long id) {
		return service.getAnime(id);
	}
	
	@GetMapping
	@ResponseBody
	public Page<AnimeView> getAllAnimes(@PageableDefault(sort="id", direction = Sort.Direction.ASC)Pageable pageable){
		return service.findAllAnimes(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public AnimeView createAnime(@RequestBody @Valid AnimeBaseReq req) {
		return service.createAnime(req);
	}
	
	@PutMapping("/{id}")
	public AnimeView updateAnime(@PathVariable(name="id") Long id,
			@RequestBody @Valid AnimeBaseReq req) {
		Anime anime = service.findAnimeorThrow(id);
		return service.updateAnime(anime, req);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAnime(@PathVariable(name="id")Long id) {
		service.deleteAnime(id);
	}

	
	
	
	
}
