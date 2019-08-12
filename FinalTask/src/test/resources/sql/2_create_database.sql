CREATE DATABASE `myDatabaseTest` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
  ON `myDatabaseTest`.*
  TO myDatabase@localhost
  IDENTIFIED BY 'clinic_password';

GRANT SELECT,INSERT,UPDATE,DELETE
  ON `myDatabaseTest`.*
  TO myDatabase_user@'%'
  IDENTIFIED BY 'clinic_password';