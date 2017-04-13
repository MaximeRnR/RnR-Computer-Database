  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'admincdb'@'172.18.0.%' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `computer-database-db-test`.* TO 'admincdb'@'172.18.0.%' WITH GRANT OPTION;


  FLUSH PRIVILEGES;

