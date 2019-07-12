USE `myDatabase`;

CREATE TABLE `user`
(
  `user_id`  int PRIMARY KEY     NOT NULL AUTO_INCREMENT,
  `login`    varchar(255) UNIQUE NOT NULL,
  `email`    varchar(255) UNIQUE NOT NULL,
  `password` varchar(255)        NOT NULL,
  `role`     ENUM ('administrator', 'visitor')
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_info`
(
  `user_id`     int PRIMARY KEY NOT NULL,
  `name`        varchar(255),
  `gender`      ENUM ('women', 'men'),
  `phoneNumber` int UNIQUE,
  `address`     varchar(255)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `pet`
(
  `id`     int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name`   varchar(255),
  `kind`   ENUM ('cat', 'dog', 'turtle', 'parrot', 'hamster'),
  `date_of_birth`    date,
  `weight` int
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `doctor`
(
  `id`   int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `event`
(
  `id`         int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `date`       date,
  `doctor_id`  int             NOT NULL,
  `service_id` int,
  `pet_id`     int             NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `coupon`
(
  `id`        int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `time`      time,
  `doctor_id` int             NOT NULL,
  `user_id`   int             NOT NULL,
  `taken`     boolean
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `service`
(
  `id`    int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `price` int,
  `name`  varchar(255)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `pet_info_about_events`
(
  `pet_id`   int PRIMARY KEY NOT NULL,
  `event_id` int             NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_info_about_pets`
(
  `user_id`     int PRIMARY KEY NOT NULL,
  `pet_id` int NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `doctor_info_about_service`
(
  `doctor_id`         int PRIMARY KEY NOT NULL,
  `service_id` int
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `doctor_info_about_pets`
(
  `doctor_id`  int PRIMARY KEY NOT NULL,
  `pet` ENUM ('cat', 'dog', 'turtle', 'parrot', 'hamster')
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `doctor_info_about_coupon`
(
  `doctor_id`  int PRIMARY KEY NOT NULL,

  `coupon_id` int NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

ALTER TABLE `user_info`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `event`
  ADD FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `event`
  ADD FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `event`
  ADD FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `coupon`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `coupon`
  ADD FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `pet_info_about_events`
  ADD FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `pet_info_about_events`
  ADD FOREIGN KEY (`event_id`) REFERENCES `event` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `user_info_about_pets`
  ADD FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `user_info_about_pets`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `doctor_info_about_coupon`
  ADD FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `doctor_info_about_coupon`
  ADD FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `doctor_info_about_pets`
  ADD FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `doctor_info_about_service`
  ADD FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `doctor_info_about_service`
  ADD FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;



