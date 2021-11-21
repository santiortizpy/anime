package com.orsami.anime.studio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.orsami.anime.anime.Anime;


@Entity
@Table(name="studio")
public class Studio {

	@Id
	@Column(name="id")
	@GenericGenerator(
			name = "studio_id_seq",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@org.hibernate.annotations.Parameter(name="sequence_name", value="studio_id_seq"),
					@org.hibernate.annotations.Parameter(name="INCREMENT", value = "1"),
					@org.hibernate.annotations.Parameter(name="MINVALUE", value="1"),
					@org.hibernate.annotations.Parameter(name="MAXVALUE", value="1"),
					@org.hibernate.annotations.Parameter(name="CACHE", value="1")
			}
			)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "studio_id_seq")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="studio",
			cascade =CascadeType.ALL,
			orphanRemoval =true)
	@JoinColumn(name="anime_id")
	private Set<Anime> animes = new HashSet<>();
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<Anime> getAnimes() {
		return animes;
	}

	public void setAnimes(Set<Anime> animes) {
		this.animes = animes;
	}
	
	
	
	}
