CREATE DATABASE `myDatabase` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
  ON `myDatabase`.*
  TO myDatabase@localhost
  IDENTIFIED BY 'clinic_password';

GRANT SELECT,INSERT,UPDATE,DELETE
  ON `myDatabase`.*
  TO myDatabase_user@'%'
  IDENTIFIED BY 'clinic_password';