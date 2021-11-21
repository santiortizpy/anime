package com.orsami.anime.anime.web;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.orsami.anime.base.BaseRequest;

public class AnimeBaseReq extends BaseRequest{
	
	@NotNull
	private String title;
	
	private String description;
	
	private String years;
	
	private String type;
	
	private int episode;
	
	private String tags;
	
	@NotNull
	private Long studioId;
	
	private List<@Valid Id> staffs;
	
	private List<@Valid Id> characters;
	
	private List<@Valid Id> reviews;

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

	public Long getStudioId() {
		return studioId;
	}

	public void setStudioId(Long studioId) {
		this.studioId = studioId;
	}

	public List<Id> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Id> staffs) {
		this.staffs = staffs;
	}

	public List<Id> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Id> characters) {
		this.characters = characters;
	}

	public List<Id> getReviews() {
		return reviews;
	}

	public void setReviews(List<Id> reviews) {
		this.reviews = reviews;
	}
	
	

}
