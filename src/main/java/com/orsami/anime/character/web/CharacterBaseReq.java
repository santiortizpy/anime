package com.orsami.anime.character.web;

import javax.validation.constraints.NotEmpty;

import com.orsami.anime.base.BaseRequest;

public class CharacterBaseReq extends BaseRequest {
	
	 @NotEmpty
	 private String name;
	 
	 private String gender;
	 
	 private String hairColor;
	 
	 private String EyesColor;
	 
	 private String tags;
	 
	 private String Type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getEyesColor() {
		return EyesColor;
	}

	public void setEyesColor(String eyesColor) {
		EyesColor = eyesColor;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
	 
	 
}
