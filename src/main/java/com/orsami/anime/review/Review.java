package com.orsami.anime.review;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.orsami.anime.studio.Studio;

@Entity
@Table(name="review")
public class Review {
	@Id
	@Column(name="id")
	@GenericGenerator(
			name = "review_id_seq",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@org.hibernate.annotations.Parameter(name="sequence_name", value="review_id_seq"),
					@org.hibernate.annotations.Parameter(name="INCREMENT", value = "1"),
					@org.hibernate.annotations.Parameter(name="MINVALUE", value="1"),
					@org.hibernate.annotations.Parameter(name="MAXVALUE", value="1"),
					@org.hibernate.annotations.Parameter(name="CACHE", value="1")
			}
			)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "review_id_seq")
	private Long id;
	
	@Column(name="stars")
	private int stars;
	
	@Column(name="user")
	private String user;
	
	@Column(name="description")
	private String description;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
		
	


}
