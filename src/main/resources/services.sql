create table if not exists services
(
	id int auto_increment
		primary key,
	izena varchar(45) null
);

INSERT INTO zerbitzuak.services (id, izena) VALUES (1, 'Dropbox');
INSERT INTO zerbitzuak.services (id, izena) VALUES (2, 'Flickr');
INSERT INTO zerbitzuak.services (id, izena) VALUES (3, 'Google Drive');
INSERT INTO zerbitzuak.services (id, izena) VALUES (4, 'Google Photos');
INSERT INTO zerbitzuak.services (id, izena) VALUES (5, 'Instagram');
