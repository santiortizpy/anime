package com.orsami.anime.anime.web;

import java.util.HashSet;
import java.util.Set;

import com.orsami.anime.character.web.CharacterView;
import com.orsami.anime.review.web.ReviewView;
import com.orsami.anime.staff.web.StaffView;
import com.orsami.anime.studio.web.StudioView;

public class AnimeView {
	private Long id;
	private String title;
	private String description;
	private String years;
	private String type;
	private int episode;
	private String tags;
	private StudioView studio;
	private Set<StaffView> staffs = new HashSet<>();
	private Set<CharacterView> characters = new HashSet<>();
	private Set<ReviewView> reviews = new HashSet<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getEpisode() {
		return episode;
	}
	public void setEpisode(int episode) {
		this.episode = episode;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public StudioView getStudio() {
		return studio;
	}
	public void setStudio(StudioView studio) {
		this.studio = studio;
	}
	public Set<StaffView> getStaffs() {
		return staffs;
	}
	public void setStaffs(Set<StaffView> staffs) {
		this.staffs = staffs;
	}
	public Set<CharacterView> getCharacters() {
		return characters;
	}
	public void setCharacters(Set<CharacterView> characters) {
		this.characters = characters;
	}
	public Set<ReviewView> getReviews() {
		return reviews;
	}
	public void setReviews(Set<ReviewView> reviews) {
		this.reviews = reviews;
	}
	
	
	
	
	

}
