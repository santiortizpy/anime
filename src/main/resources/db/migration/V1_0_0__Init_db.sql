CREATE SEQUENCE studio_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;
	
CREATE TABLE studios (
	id int8 NOT NULL DEFAULT nextval('studio_id_seq'),
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	PRIMARY KEY (id)
);

CREATE SEQUENCE staff_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;
	
CREATE TABLE staffs(
	id int8 NOT NULL DEFAULT nextval('staff_id_seq'),
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	birth_place VARCHAR(255),
	birth_day  TIMESTAMP,
	PRIMARY KEY(id));
	
CREATE SEQUENCE review_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;
	
CREATE TABLE reviews(
	id int8 NOT NULL DEFAULT nextval('review_id_seq'),
	stars int4,
	"user" VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	PRIMARY KEY(id));
	
CREATE SEQUENCE character_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;
CREATE TABLE characters(
	id int8 NOT NULL DEFAULT nextval('character_id_seq'),
	name VARCHAR(255) NOT NULL,
	gender VARCHAR(255),
	hair_color VARCHAR(255),
	eyes_color VARCHAR(255),
	tags VARCHAR(255),
	"type" VARCHAR(255),
	PRIMARY KEY(id));

CREATE SEQUENCE anime_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	MAXVALUE 2147483647
	CACHE 1;

CREATE TABLE animes(
	id int8 NOT NULL DEFAULT nextval('anime_id_seq'),
	title VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	years VARCHAR(255),
	"type"  VARCHAR(255),
	episode int4,
	tags VARCHAR(255),
	studio_id int8,
	PRIMARY KEY (id)
	
);

CREATE TABLE anime_staff(
 anime_id int8 NOT NULL,
 staff_id int8 NOT NULL,
 PRIMARY KEY (anime_id, staff_id)
);

CREATE TABLE anime_character(
 anime_id int8 NOT NULL,
 character_id int8 NOT NULL,
 PRIMARY KEY(anime_id,character_id));
 
 CREATE TABLE anime_review(
 	anime_id int8 NOT NULL,
 	review_id int8 NOT NULL,
 	PRIMARY KEY(anime_id,review_id)
 	);
 	
 ALTER TABLE if EXISTS  anime ADD CONSTRAINT studio_fk
 FOREIGN KEY (studio_id) references studios(id);
 	
 ALTER TABLE if EXISTS anime_staff ADD CONSTRAINT staff_fk
 FOREIGN KEY (staff_id) references staffs(id);
 ALTER TABLE if EXISTS anime_staff ADD CONSTRAINT anime_fk
 FOREIGN KEY (anime_id)  references animes(id);
 ALTER TABLE if EXISTS anime_character ADD CONSTRAINT anime_fk
 FOREIGN KEY (anime_id) references animes(id);
 ALTER TABLE if EXISTS anime_character ADD CONSTRAINT character_fk
 FOREIGN KEY (character_id) references characters(id);
 ALTER TABLE if EXISTS anime_review ADD CONSTRAINT anime_fk
 FOREIGN KEY (anime_id) references animes(id);
 ALTER TABLE if EXISTS anime_review ADD CONSTRAINT review_fk
 FOREIGN KEY (review_id) references reviews(id);	 
 	
 
