insert into customer(id, first_name, last_name, email, phone_number) values (1, 'Sofia', 'Elgort', 'sophielg@gmail.com', '0731128932');
insert into customer(id, first_name, last_name, email, phone_number) values (2, 'Alex', 'Munteanu', 'alexmunteanu@gmail.com', '0755192882');
insert into customer(id, first_name, last_name, email, phone_number) values (3, 'Octavian', 'Ana', 'octav_ana@gmail.com', '0742384405');

insert into movie(id, title, duration, genre) values (1, 'Gone with the wind', 150, 'DRAMA');
insert into info(id, movie_id, description, director, rating) values (1, 1, 'Based on a true story', 'John Libelich', 8);

insert into movie(id, title, duration, genre) values (2, 'Parasite', 100, 'FAMILY');
insert into info(id, movie_id, description, director, rating) values (2, 2, 'Based on a true story', 'Mara Mick', 9);


insert into actor(id, first_name, last_name, date_of_birth) values (1, 'John', 'Travolta', '1974-10-21');
insert into actor(id, first_name, last_name, date_of_birth) values (2, 'Angelina', 'Jolie', '1981-05-02');
insert into actor(id, first_name, last_name, date_of_birth) values (3, 'George', 'Cloney', '1969-03-14');
insert into actor(id, first_name, last_name, date_of_birth) values (4, 'Alexander', 'Al Pacino', '1958-02-15');
insert into actor(id, first_name, last_name, date_of_birth) values (5, 'Will', 'Smith', '1985-06-21');

insert into movie_actor(movie_id, actor_id) values (1, 3);
insert into movie_actor(movie_id, actor_id) values (1, 4);
insert into movie_actor(movie_id, actor_id) values (1, 5);
insert into movie_actor(movie_id, actor_id) values (2, 1);
insert into movie_actor(movie_id, actor_id) values (2, 4);

insert into venue(id, venue_name, location_name, seat_capacity) values (1, 'Sala 1', 'Afi Cinema Bucuresti', 100);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (1, '2022-10-21', '20:00', 40, 1, 1);