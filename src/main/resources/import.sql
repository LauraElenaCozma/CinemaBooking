-- insert into `user`(id, first_name, last_name, email, phone_number) values (1, 'Sofia', 'Elgort', 'sophielg@gmail.com', '0731128932');
-- insert into `user`(id, first_name, last_name, email, phone_number) values (2, 'Alex', 'Munteanu', 'alexmunteanu@gmail.com', '0755192882');
-- insert into `user`(id, first_name, last_name, email, phone_number) values (3, 'Octavian', 'Ana', 'octav_ana@gmail.com', '0742384405');

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

insert into venue(id, venue_name, location_name, seat_capacity) values (1, 'Sala 1', 'Afi Cinema Bucuresti', 15);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (1, '2022-10-21', '20:00', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (2, '2022-10-22', '20:00', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (3, '2022-10-23', '20:00', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (4, '2022-10-30', '20:30', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (5, '2022-07-01', '20:30', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (6, '2022-07-05', '20:30', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (7, '2022-07-10', '20:00', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (8, '2022-07-11', '20:00', 50, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (9, '2022-07-12', '20:30', 50, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (10, '2022-07-12', '18:30', 20, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (11, '2022-06-23', '19:30', 20, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (12, '2022-06-23', '19:30', 40, 2, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (13, '2022-06-23', '16:30', 30, 2, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (14, '2022-06-10', '16:30', 30, 2, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (15, '2022-10-21', '20:00', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (16, '2022-10-22', '20:00', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (17, '2022-10-23', '20:00', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (18, '2022-10-30', '20:30', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (19, '2022-07-01', '20:30', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (20, '2022-07-05', '20:30', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (21, '2022-07-10', '20:00', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (22, '2022-07-11', '20:00', 50, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (23, '2022-07-12', '20:30', 50, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (24, '2022-07-12', '18:30', 20, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (25, '2022-06-23', '19:30', 20, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (26, '2022-06-23', '19:30', 40, 2, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (27, '2022-06-23', '16:30', 30, 2, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (28, '2022-06-10', '16:30', 30, 2, 1);