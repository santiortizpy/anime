package com.orsami.anime.review.web;

import javax.validation.constraints.NotEmpty;

import com.orsami.anime.base.BaseRequest;

public class ReviewBaseReq extends BaseRequest {
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String user;
	
	private int star;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}
	
	

}
