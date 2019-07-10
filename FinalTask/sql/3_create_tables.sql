USE `myDatabase`;

CREATE TABLE `user`
(
  `user_id`  int PRIMARY KEY     NOT NULL,
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
  `address`     varchar(255),
  `pet`         int             NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `pet_info`
(
  `pet_id` int PRIMARY KEY NOT NULL,
  `name`   varchar(255),
  `kind`   ENUM ('cat', 'dog', 'turtle', 'parrot', 'hamster'),
  `age`    int,
  `weight` int,
  `event`  int
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `doctor_info`
(
  `doctor_id` int PRIMARY KEY NOT NULL,
  `name`      varchar(255),
  `service`   int             NOT NULL,
  `pet`       ENUM ('cat', 'dog', 'turtle', 'parrot', 'hamster')
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `event`
(
  `event_id`   int PRIMARY KEY NOT NULL,
  `date`       date,
  `doctor`     int             NOT NULL,
  `service_id` int,
  `pet`        int             NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `service_info`
(
  `service_id` int PRIMARY KEY NOT NULL,
  `price`      int,
  `service`    ENUM ('vaccination', 'consultation', 'sterilization', 'castration', 'denistry', 'microchipping', 'wellness_exam', 'analysis')
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_need`
(
  `service` int,
  `user`    int,
  `pet`     int,
  `doctor`  int
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

ALTER TABLE `user_info`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `user_info`
  ADD FOREIGN KEY (`pet`) REFERENCES `pet_info` (`pet_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `pet_info`
  ADD FOREIGN KEY (`event`) REFERENCES `event` (`event_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `event`
  ADD FOREIGN KEY (`doctor`) REFERENCES `doctor_info` (`doctor_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `doctor_info`
  ADD FOREIGN KEY (`service`) REFERENCES `service_info` (`service_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `event`
  ADD FOREIGN KEY (`service_id`) REFERENCES `service_info` (`service_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `user_need`
  ADD FOREIGN KEY (`service`) REFERENCES `service_info` (`service_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `user_need`
  ADD FOREIGN KEY (`user`) REFERENCES `user_info` (`user_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `user_need`
  ADD FOREIGN KEY (`pet`) REFERENCES `pet_info` (`pet_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `user_need`
  ADD FOREIGN KEY (`doctor`) REFERENCES `doctor_info` (`doctor_id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

