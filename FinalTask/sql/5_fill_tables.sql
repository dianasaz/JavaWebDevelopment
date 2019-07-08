INSERT INTO `user` (`user_id`,
                    `login`,
                    `password`,
                    `email`,
                    `role`)
VALUES (2,
        'user1',
        'EE11CBB19052E40B07AAC0CA060C23EE', /* MD5 хэш пароля "user" */
        'das@mail.ru',
        1),
       (3,
        'user2',
        'WW34CBB19052E40B07AAC0CA060C23EE',
        'ERT@mail.ru',
        1);

INSERT INTO `user_info`
  (`user_id`, `name`, 'gender', `phoneNumber`, `address`, `pet`)
VALUES (2, 'Иванов Иван Иванович', 'MEN', 375291011102, 'ул. Покровского, 13-3', 1),
       (3, 'Козлов Сергей Петрович', 'MEN', 375291981102, 'ул. Покровского, 15-3', 2);

INSERT INTO `pet_info`
(pet_id, `name`, `kind`, `age`, `weight`, `event`)
VALUES (1, 'Петя', 'PARROT', 5, 860, 1),
       (2, 'Вася', 'CAT', 2, 3810, 2);

INSERT INTO `doctor_info`
  (`doctor_id`, `name`, `service`, `pet`)
VALUES (1, 'Наталья Александровна Миронова', 1, 'DOG'),
       (2, 'Николай Васильевич Зуев', 8, 'CAT'),
       (3, 'Василий Романович Треска', 5, 'PARROT'),
       (4, 'Марина Геннадьвна Жукова', 3, 'TURTLE'),
       (5, 'Федор Константинович Печкин', 2, 'HAMSTER'),
       (6, 'Анна Алексеевна Рига', 7, 'DOG');

INSERT INTO `event`
(`event_id`, `date`, `doctor`, `service_id`, `pet`)
VALUES (1, '2006-09-11', 3,  5, 1),
       (2,'2006-12-27', 2,  8, 2);

INSERT INTO `service_info`
(`service_id`, `price`, `service`)
VALUES (1, 120,'VACCINATION'),
       (2, 190, 'CONSULTATION'),
       (3, 70,  'STERILIZATION'),
       (4, 25, 'CASTRATION'),
       (5, 50, 'DENTISTRY'),
       (6, 230, 'MICROCHIPPING'),
       (7, 45, 'WELLNESS_EXAM'),
       (8, 90, 'ANALYSIS');

INSERT INTO `user_need`
(`service`, `user`, `pet`, `doctor`)
VALUES (1, 1, 1, 1),
       (8, 2, 2, 2);