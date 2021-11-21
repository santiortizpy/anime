package com.orsami.anime.studio.web;

import javax.validation.constraints.NotEmpty;

import com.orsami.anime.base.BaseRequest;

public class StudioBaseReq  extends BaseRequest{
	
	@NotEmpty
	private String name;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
