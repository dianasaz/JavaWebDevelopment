USE `myDatabase`;
INSERT INTO `user` (`user_id`,
                    `login`,
                    `password`,
                    `email`,
                    `role`)
VALUES (2,
        'user1',
        'EE11CBB19052E40B07AAC0CA060C23EE',
        'das@mail.ru',
        2),
       (3,
        'user2',
        'WW34CBB19052E40B07AAC0CA060C23EE',
        'ERT@mail.ru',
        2);

INSERT INTO `user_info`
  (`user_id`, `name`, 'gender', `phoneNumber`, `address`)
VALUES (2, 'Иванов Иван Иванович', 'MEN', 375291011102, 'ул. Покровского, 13-3'),
       (3, 'Козлов Сергей Петрович', 'MEN', 375291981102, 'ул. Покровского, 15-3');

INSERT INTO `pet`
(id, `name`, `kind`, `date_of_birth`, `weight`, `user_id`)
VALUES (1, 'Петя', 'PARROT', '2000-07-08', 860, 1),
       (2, 'Вася', 'CAT', 2, '2013-02-03', 2);

INSERT INTO `doctor`
  (`id`, `name`)
VALUES (1, 'Наталья Александровна Миронова'),
       (2, 'Николай Васильевич Зуев'),
       (3, 'Василий Романович Треска'),
       (4, 'Марина Геннадьвна Жукова'),
       (5, 'Федор Константинович Печкин'),
       (6, 'Анна Алексеевна Рига');

INSERT INTO `event`
(`id`, `date`, `doctor_id`, `service_id`, `pet_id`)
VALUES (1, '2006-09-11', 3,  5, 1),
       (2,'2006-12-27', 2,  8, 2);

INSERT INTO `service`
(`id`, `price`, `name`)
VALUES (1, 120,'VACCINATION'),
       (2, 190, 'CONSULTATION'),
       (3, 70,  'STERILIZATION'),
       (4, 25, 'CASTRATION'),
       (5, 50, 'DENTISTRY'),
       (6, 230, 'MICROCHIPPING'),
       (7, 45, 'WELLNESS_EXAM'),
       (8, 90, 'ANALYSIS');

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
