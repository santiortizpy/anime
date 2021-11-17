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

import com.orsami.anime.studio.Studio;

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
	private long id;
	
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
    @JoinColumn(name = "id_studio")
    private Studio studio;

	
	@Column(name="staff")
	private String staff;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "anime_character",
            joinColumns = { @JoinColumn(name = "id_anime") },
            inverseJoinColumns = { @JoinColumn(name = "id_character") })
    private Set<Character> characters = new HashSet<>();

	
	@Column(name="review")
	private String review;
}
