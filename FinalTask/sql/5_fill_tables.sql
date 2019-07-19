USE `myDatabase`;
INSERT INTO `user` (                    `login`,
                    `password`,
                    `role`)
VALUES ('diana',
        'EE11CBB19052E40B07AAC0CA060C23EE',
        2),
       ('vadim',
        'b45cffe084dd3d20d928bee85e7b0f21',
        2);

INSERT INTO `user_info`
  (`name`, `gender`, `email`, `phoneNumber`, `address`)
VALUES ('Иванов Иван Иванович', 1, 'din@mail.ru', 291011102, 'ул. Покровского, 13-1'),
       ('Козлов Сергей Петрович', 1, 'dinqwe@mail.ru', 291981102, 'ул. Покровского, 15-3');

INSERT INTO `pet`
(`name`, `kind`, `date_of_birth`, `weight`, `user_id`)
VALUES ('Петя', 3, '2000-07-08', 860, 1),
       ('Вася', 1, '2013-02-03', 700, 2);

INSERT INTO `doctor`
  (`name`)
VALUES ('Наталья Александровна Миронова'),
       ('Николай Васильевич Зуев'),
       ('Василий Романович Треска'),
       ('Марина Геннадьвна Жукова'),
       ('Федор Константинович Печкин'),
       ('Анна Алексеевна Рига');

INSERT INTO `by.sazanchuk.finalTask.service`
( `price`, `name`)
VALUES (120,'VACCINATION'),
       (190, 'CONSULTATION'),
       (70,  'STERILIZATION'),
       (25, 'CASTRATION'),
       (50, 'DENTISTRY'),
       (230, 'MICROCHIPPING'),
       (45, 'WELLNESS_EXAM'),
       (90, 'ANALYSIS');

INSERT INTO `event`
(`date`, `doctor_id`, `service_id`, `pet_id`)
VALUES ('2006-09-11', 3,  5, 1),
       ('2006-12-27', 2,  8, 2);

INSERT INTO `user_pet`
(`user_id`, `pet_id`)
VALUES (1, 1),
       (2, 2);

INSERT INTO `doctor_service`
(`doctor_id`, `service_id`)
VALUES (1, 1),
       (2, 2),
       (2, 7),
       (1, 6),
       (3, 4),
       (4, 1),
       (5, 2),
       (6, 5),
       (4, 8),
       (5, 3);
