CREATE TABLE `user` (
  `id` bigint NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_salt` varchar(255) DEFAULT NULL,
  `create_at` timestamp null default now(),
  `update_at` timestamp null default now(),
  PRIMARY KEY (`id`)
);

CREATE UNIQUE INDEX ux_USER_EMAIL ON "USER" (EMAIL);
CREATE UNIQUE INDEX ux_USER_NAME ON "USER" (NAME);

CREATE TABLE `follower` (
  `id` bigint NOT NULL auto_increment,
  `follower_id` bigint NOT NULL,
  `following_id` bigint NOT NULL,
  `create_at` timestamp null default now(),
  PRIMARY KEY (`id`)
);

CREATE UNIQUE INDEX ux_follower_id_following_id ON follower (follower_id, following_id);
CREATE INDEX idx_following_id_follower_id ON follower (following_id, follower_id);

CREATE TABLE `post` (
  `id` bigint NOT NULL auto_increment,
  `content` varchar(2000) NOT NULL,
  `user_id` bigint NOT NULL,
  `create_at` timestamp null default now(),
  `update_at` timestamp null default now(),
  PRIMARY KEY (`id`)
);

CREATE INDEX idx_post_user_id ON post (user_id);