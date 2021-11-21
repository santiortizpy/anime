package com.orsami.anime.anime;

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

import com.orsami.anime.review.Review;
import com.orsami.anime.staff.Staff;
import com.orsami.anime.studio.Studio;
import com.orsami.anime.character.Character;

@Entity
@Table(name="anime")
public class Anime {

	@Id
	@Column(name="id")
	@GenericGenerator(
			name = "anime_id_seq",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@org.hibernate.annotations.Parameter(name="sequence_name", value="anime_id_seq"),
					@org.hibernate.annotations.Parameter(name="INCREMENT", value = "1"),
					@org.hibernate.annotations.Parameter(name="MINVALUE", value="1"),
					@org.hibernate.annotations.Parameter(name="MAXVALUE", value="1"),
					@org.hibernate.annotations.Parameter(name="CACHE", value="1")
			}
			)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "anime_id_seq")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="years")
	private String years;
	
	@Column(name="type")
	private String type;
	
	@Column(name="episode")
	private int episode;
	
	@Column(name="tags")
	private String tags;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "studio_id")
    private Studio studio;

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "anime_staff",
            joinColumns = { @JoinColumn(name = "anime_id") },
            inverseJoinColumns = { @JoinColumn(name = "staff_id") })
    private Set<Staff> staffs = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "anime_character",
            joinColumns = { @JoinColumn(name = "anime_id") },
            inverseJoinColumns = { @JoinColumn(name = "character_id") })
    private Set<Character> characters = new HashSet<>();

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "anime_review",
            joinColumns = { @JoinColumn(name = "anime_id") },
            inverseJoinColumns = { @JoinColumn(name = "review_id") })
    private Set<Review> review = new HashSet<>();


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


	public Studio getStudio() {
		return studio;
	}


	public void setStudio(Studio studio) {
		this.studio = studio;
	}


	public Set<Staff> getStaffs() {
		return staffs;
	}


	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}


	public Set<Character> getCharacters() {
		return characters;
	}


	public void setCharacters(Set<Character> characters) {
		this.characters = characters;
	}


	public Set<Review> getReview() {
		return review;
	}


	public void setReview(Set<Review> review) {
		this.review = review;
	}
	
	
}
