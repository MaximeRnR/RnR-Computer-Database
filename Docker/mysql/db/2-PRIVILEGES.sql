  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'admincdb'@'172.21.0.%' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `computer-database-db-test`.* TO 'admincdb'@'172.21.0.%' WITH GRANT OPTION;


  FLUSH PRIVILEGES;

  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'admincdb'@'172.20.0.%' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `computer-database-db-test`.* TO 'admincdb'@'172.20.0.%' WITH GRANT OPTION;


  FLUSH PRIVILEGES;

