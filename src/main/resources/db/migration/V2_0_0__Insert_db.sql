INSERT INTO studios(name,description)VALUES ('WIT studio','List of anime from wit Studio');
INSERT INTO studios(name,description)VALUES ('EKACHI EPILKA','anime from ECKACHI');

INSERT INTO staffs(name, description,birth_place,birth_day)
VALUES ('Yoshinobu kasai','best known for directing','japan','1992-01-08');
INSERT INTO staffs(name, description,birth_place,birth_day)
VALUES('Hajime Isayama','is best known for being the original creator of Attack of Titan',
'japan','1986-08-29');

INSERT INTO characters(name,gender,hair_color, eyes_color, tags, "type")
VALUES ('Akari Sawake','Female','black','blue','fast','no type');
INSERT INTO characters(name,gender,hair_color, eyes_color, tags, "type")
VALUES('Eren Jaeger','Male','brown','green','military','smart');

INSERT INTO reviews(stars, "user",description)
VALUES (3,'ortizsan','good anime');
INSERT INTO reviews(stars, "user",description)
VALUES (5,'eiraza','Attack on Titan season 3 part 2 answers sooo questions');
INSERT INTO animes(title,description, years,"type",episode,tags,studio_id)
VALUES
('180byou de kimi no mimi wo shiawase','the story center on akari',
'2021','anime',5,'short episodes',2);
INSERT INTO animes(title,description, years,"type",episode,tags,studio_id) VALUES
('Attack on Titan 3rd Season:Part II','the battle to retake wall Maria begins',
'2019','fantasy',5,'military',1);

