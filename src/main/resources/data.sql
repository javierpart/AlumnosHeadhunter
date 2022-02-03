INSERT INTO role (description, name) VALUES ('Admin role', 'ADMIN');
INSERT INTO role (description, name) VALUES ('Recruiter role', 'RECRUITER');


INSERT INTO super_admin( email, hash_password) VALUES ( 'hola@gmail.com', '$2a$10$1tPmaTlaVu6T3a7FiWxBd.gMmv23469gKaLCQiI2IW2dpBCnE1d.');
INSERT INTO admin_roles(admin_id, role_id) VALUES (1, 1);

INSERT INTO recruiter(last_name, public_id, name, email, hash_password, register_attempts, is_registered, company, phone_number, profile_imageurl, hashed_email) values ('perez', 'fgw8e724', 'javier','recruiter@gmail.com', '$2a$10$aKvUCpB1o334BGy/l5mgAu7qxjqwO7yeyP/wCD3yB1WjGzJBS14ny', 0 , true, 'openbootcamp', '666555444', 'https://192.com', '$2a$10$NwJ08rIphobCbK4czOjxceWdNE.ZFNtdkZrX7MEBS2f1FqJWdOaTq');
INSERT INTO recruiter_roles(recruiter_id, role_id) VALUES (1, 2);


INSERT INTO ob_technology(public_id, name) VALUES ('fgihj3', 'Spring');
INSERT INTO ob_technology(public_id, name) VALUES ('vi2345hi2', 'React');
INSERT INTO ob_technology(public_id, name) VALUES ('sdf87yt92', 'Java');
INSERT INTO ob_technology(public_id, name) VALUES ('sdg3q45t3w', 'Hibernate');
INSERT INTO ob_technology(public_id, name) VALUES ('werg324bt', 'Javascript');
INSERT INTO ob_technology(public_id, name) VALUES ('tyj5642', 'HTML&CSS');


INSERT INTO ob_user(public_id, name, last_name, email, phone_number, country, city, city_transfer, remote, cv_url, image_url) VALUES ('gh9e8w7rgh', 'juan', 'garcia', 'auwdg@gmail.com', '765867345', 'Spain', 'Málaga', false, 'REMOTE', 'http://www.africau.edu/images/default/sample.pdf', 'https://i.imgur.com/QKG5Sac.jpeg');
INSERT INTO ob_user(public_id, name, last_name, email, phone_number, country, city, city_transfer, remote, cv_url, image_url) VALUES ('wef2345123', 'maría', 'perez', 'au123wdg@gmail.com', '12345634', 'Spain', 'Málaga', true, 'REMOTE', 'http://www.africau.edu/images/default/sample.pdf', 'https://i.imgur.com/QKG5Sac.jpeg');
INSERT INTO ob_user(public_id, name, last_name, email, phone_number, country, city, city_transfer, remote, cv_url, image_url) VALUES ('sdfwerwer', 'juan', 'garcia', 'debherhg@gmail.com', '23452134', 'Spain', 'Madrid', false, 'BOTH', 'http://www.africau.edu/images/default/sample.pdf', 'https://i.imgur.com/QKG5Sac.jpeg');
INSERT INTO ob_user(public_id, name, last_name, email, phone_number, country, city, city_transfer, remote, cv_url, image_url) VALUES ('werwe32', 'Laura', 'soria', 'ertert@gmail.com', '45674544', 'Spain', 'Málaga', true, 'REMOTE', 'http://www.africau.edu/images/default/sample.pdf', 'https://i.imgur.com/QKG5Sac.jpeg');
INSERT INTO ob_user(public_id, name, last_name, email, phone_number, country, city, city_transfer, remote, cv_url, image_url) VALUES ('dfgherg3', 'lorena', 'gallardo', 'gerg@gmail.com', '657585643', 'Spain', 'Huelva', false, 'BOTH', 'http://www.africau.edu/images/default/sample.pdf', 'https://i.imgur.com/QKG5Sac.jpeg');
INSERT INTO ob_user(public_id, name, last_name, email, phone_number, country, city, city_transfer, remote, cv_url, image_url) VALUES ('sdf23345234', 'juan', 'rodriguez', 'g@gmail.com', '23423567', 'Spain', 'Málaga', true, 'REMOTE', 'http://www.africau.edu/images/default/sample.pdf', 'https://i.imgur.com/QKG5Sac.jpeg');
INSERT INTO ob_user(public_id, name, last_name, email, phone_number, country, city, city_transfer, remote, cv_url, image_url) VALUES ('tewrgeg34', 'juan', 'garcia', 'reeterydfg@gmail.com', '1234246234', 'Spain', 'Cádiz', false, 'IN_PERSON', 'http://www.africau.edu/images/default/sample.pdf', 'https://i.imgur.com/QKG5Sac.jpeg');

INSERT INTO users_tags(users_id, tags_id) VALUES (1,1);
INSERT INTO users_tags(users_id, tags_id) VALUES (1,2);
INSERT INTO users_tags(users_id, tags_id) VALUES (1,3);
INSERT INTO users_tags(users_id, tags_id) VALUES (2,1);
INSERT INTO users_tags(users_id, tags_id) VALUES (4,1);