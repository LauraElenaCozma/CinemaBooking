insert into movie(id, title, duration, genre) values (1, 'Gone with the wind', 150, 'DRAMA');
insert into info(id, movie_id, description, director, rating) values (1, 1, 'The manipulative daughter of a Georgia plantation owner conducts a turbulent romance with a roguish profiteer during the American Civil War and Reconstruction periods.', 'Victor Fleming', 8.2);

insert into movie(id, title, duration, genre) values (2, 'Parasite', 100, 'THRILLER');
insert into info(id, movie_id, description, director, rating) values (2, 2, 'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.', 'Bong Joon Ho', 8.5);

insert into movie(id, title, duration, genre) values (3, 'Schindler''s List', 195, 'DRAMA');
insert into info(id, movie_id, description, director, rating) values (3, 3, 'In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.', 'Steven Spielberg', 9);

insert into movie(id, title, duration, genre) values (4, 'Forrest Gump', 142, 'ROMANCE');
insert into info(id, movie_id, description, director, rating) values (4, 4, 'The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.', 'Robert Zemeckis', 8.9);

insert into movie(id, title, duration, genre) values (5, 'La vita e bella', 122, 'ROMANCE');
insert into info(id, movie_id, description, director, rating) values (5, 5, 'When an open-minded Jewish waiter and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp.', 'Roberto Benigni', 8.6);

insert into movie(id, title, duration, genre) values (6, 'Saving Private Ryan', 150, 'ACTION');
insert into info(id, movie_id, description, director, rating) values (6, 6, 'Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.', 'Robert Rodat', 8.6);



insert into actor(id, first_name, last_name, date_of_birth) values (1, 'Liam', 'Neeson', '1974-10-21');
insert into actor(id, first_name, last_name, date_of_birth) values (2, 'Ben', 'Ben Kingsley', '1981-05-02');
insert into actor(id, first_name, last_name, date_of_birth) values (3, 'Vivien', 'Leigh', '1896-03-14');
insert into actor(id, first_name, last_name, date_of_birth) values (4, 'Thomas', 'Mitchell', '1892-02-15');
insert into actor(id, first_name, last_name, date_of_birth) values (5, 'Barbara', 'O''Neil', '1885-06-21');
insert into actor(id, first_name, last_name, date_of_birth) values (6, 'Kang-ho', 'Song', '1985-07-21');
insert into actor(id, first_name, last_name, date_of_birth) values (7, 'Tom', 'Hanks', '1965-03-27');
insert into actor(id, first_name, last_name, date_of_birth) values (8, 'Robin', 'Wright', '1955-02-01');
insert into actor(id, first_name, last_name, date_of_birth) values (9, 'Roberto', 'Benigni', '1955-01-10');
insert into actor(id, first_name, last_name, date_of_birth) values (10, 'Nicoletta', 'Braschi', '1965-03-10');

insert into movie_actor(movie_id, actor_id) values (1, 3);
insert into movie_actor(movie_id, actor_id) values (1, 4);
insert into movie_actor(movie_id, actor_id) values (1, 5);
insert into movie_actor(movie_id, actor_id) values (3, 1);
insert into movie_actor(movie_id, actor_id) values (3, 2);
insert into movie_actor(movie_id, actor_id) values (2, 6);
insert into movie_actor(movie_id, actor_id) values (4, 7);
insert into movie_actor(movie_id, actor_id) values (4, 8);
insert into movie_actor(movie_id, actor_id) values (6, 7);
insert into movie_actor(movie_id, actor_id) values (5, 9);
insert into movie_actor(movie_id, actor_id) values (5, 10);


insert into venue(id, venue_name, location_name, seat_capacity) values (1, 'Sala 1', 'Afi Cinema Bucuresti', 15);
insert into venue(id, venue_name, location_name, seat_capacity) values (2, 'Sala 2', 'Afi Cinema Bucuresti', 20);
insert into venue(id, venue_name, location_name, seat_capacity) values (3, 'Sala 1', 'Park Lake Cinema Bucuresti', 30);
insert into venue(id, venue_name, location_name, seat_capacity) values (4, 'Sala 2', 'Park Lake Cinema Bucuresti', 100);
insert into venue(id, venue_name, location_name, seat_capacity) values (5, 'Sala 3', 'Park Lake Cinema Bucuresti', 80);
insert into venue(id, venue_name, location_name, seat_capacity) values (6, 'Sala 4', 'Park Lake Cinema Bucuresti', 70);
insert into venue(id, venue_name, location_name, seat_capacity) values (7, 'Sala 5', 'Park Lake Cinema Bucuresti', 50);
insert into venue(id, venue_name, location_name, seat_capacity) values (8, 'Sala 1', 'Sun Plaza Cinema Bucuresti', 80);
insert into venue(id, venue_name, location_name, seat_capacity) values (9, 'Sala 2', 'Sun Plaza Cinema Bucuresti', 80);

insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (1, '2022-06-21', '21:00', 40, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (2, '2022-06-22', '20:00', 45, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (3, '2022-06-23', '20:30', 40, 1, 2);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (4, '2022-06-21', '20:30', 30, 2, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (5, '2022-07-01', '20:30', 40, 2, 2);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (6, '2022-07-05', '20:30', 40, 3, 3);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (7, '2022-07-10', '20:00', 40, 3, 3);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (8, '2022-07-11', '20:00', 50, 4, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (9, '2022-07-12', '20:30', 50, 5, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (10, '2022-07-12', '18:30', 20, 6, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (11, '2022-06-23', '19:30', 20, 1, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (12, '2022-06-23', '19:30', 40, 2, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (13, '2022-06-23', '16:30', 30, 3, 4);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (14, '2022-06-10', '16:30', 30, 3, 5);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (15, '2022-10-21', '20:00', 40, 3, 8);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (16, '2022-10-22', '20:00', 40, 4, 2);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (17, '2022-10-23', '20:00', 40, 4, 5);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (18, '2022-10-30', '20:30', 40, 4, 6);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (19, '2022-07-01', '20:30', 40, 5, 2);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (20, '2022-07-05', '20:30', 40, 5, 7);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (21, '2022-07-10', '20:00', 40, 5, 9);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (22, '2022-07-11', '20:00', 50, 6, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (23, '2022-07-12', '20:30', 50, 6, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (24, '2022-07-12', '18:30', 20, 1, 9);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (25, '2022-06-23', '19:30', 20, 1, 8);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (26, '2022-06-23', '19:30', 40, 2, 1);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (27, '2022-06-23', '16:30', 30, 1, 5);
insert into movie_showing(id, date, hour, price, movie_id, venue_id) values (28, '2022-06-10', '16:30', 30, 1, 6);
