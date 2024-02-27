insert into `user` (  `name`  ,  `email` ,  `password`)
values('Jack','jack@example.com','{bcrypt}$2a$10$oO739LQrUgK3HO9.Yd5IOOaGinCwnfvGzylgrm7qBj0d5EJ8Frnb2');
insert into `user` (  `name`  ,  `email` ,  `password`)
values('Tom','tom@example.com','{bcrypt}$2a$10$oO739LQrUgK3HO9.Yd5IOOaGinCwnfvGzylgrm7qBj0d5EJ8Frnb2');
insert into `user` (  `name`  ,  `email` ,  `password`)
values('Lucy','lucy@example.com','{bcrypt}$2a$10$oO739LQrUgK3HO9.Yd5IOOaGinCwnfvGzylgrm7qBj0d5EJ8Frnb2');
insert into `user` (  `name`  ,  `email` ,  `password`)
values('John','john@example.com','{bcrypt}$2a$10$oO739LQrUgK3HO9.Yd5IOOaGinCwnfvGzylgrm7qBj0d5EJ8Frnb2');

insert into `post` (  `user_id`  ,  `content`)
values(1,'hello world' );
insert into `post` (  `user_id`  ,  `content`)
values(1,'hello world' );


insert into `follower` (  `follower_id`  ,  `following_id`)
values
(1, 2 ),
(1, 3 );
