package com.orsami.anime.staff;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="staff")
public class Staff {
	@Id
	@Column(name="id")
	@GenericGenerator(
			name = "staff_id_seq",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@org.hibernate.annotations.Parameter(name="sequence_name", value="staff_id_seq"),
					@org.hibernate.annotations.Parameter(name="INCREMENT", value = "1"),
					@org.hibernate.annotations.Parameter(name="MINVALUE", value="1"),
					@org.hibernate.annotations.Parameter(name="MAXVALUE", value="1"),
					@org.hibernate.annotations.Parameter(name="CACHE", value="1")
			}
			)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "staff_id_seq")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="birth_place")
	private String birthPlace;
	
	@Column(name="birth_day")
	private Date birthDay;

	

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

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	

}
