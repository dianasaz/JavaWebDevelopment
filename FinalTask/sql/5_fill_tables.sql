USE `myDatabase`;
INSERT INTO `user` (`login`,
                    `password`,
                    `role`,
                    `email`,
                    `name`,
                    `phoneNumber`,
                    `address`)
VALUES ('diana',
        'EE11CBB19052E40B07AAC0CA060C23EE',
        2,
        'din@mail.ru',
        'Иванов Иван Иванович',
        291011102,
        'ул. Покровского, 13-1'),
       ('vadim',
        'b45cffe084dd3d20d928bee85e7b0f21',
        2,
        'dinqwe@mail.ru',
        'Козлов Сергей Петрович',
        291981102,
        'ул. Покровского, 15-3');

