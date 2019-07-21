USE `myDatabase`;
INSERT INTO `user` (`login`,
                    `password`,
                    `role`,
                    `email`,
                    `name`,
                    `gender`,
                    `phoneNumber`,
                    `address`)
VALUES ('admin',
        '21232F297A57A5A743894A0E4A801FC3', /* MD5 хэш пароля "admin" */
        1,
        'dinka222@mail.ru',
        'Diana Sazanchuk',
        1,
        29108326,
        'Дзержинского 20');