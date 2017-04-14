# RnR-Computer-Database

[Excilys Training formation](https://github.com/excilys/training-java "Github of the formation")

A 2-month fulltime java training around an incremental project. Java / JSP / Servlet / Maven / JUnit / Mockito / Selenium / Spring / Hibernate / JPA / Hikari CP / Jackson / Spring MVC / Spring Security / Gatling / Docker

[WebSite of my project](http://52.53.230.21:32810/ComputerDatabase/dashboard "Website")




### Dependencies

1. JRE 8
2. JUnit 4
3. commons-db-utils1.6
4. mysql-connector-java-5.1.41
5. slf4j-api
6. logback-classic 1.2.2
7. logback-core 1.2.2
8. mockito-all
9. javax.servlet-api
10. jsp-api
11. jstl
12. HikariCP
13. mariadb-java-client
14. selenium-server


# Docker 

## Setup des dockers

```bash
docker network create --subnet 172.20.0.0/16 --gateway 172.20.0.1 --driver bridge prod_network
docker network create --subnet 172.18.0.0/16 --gateway 172.18.0.1 --driver bridge test_network

docker volume create --name WAR
docker volume create --name CDB_Volume

docker build -t mrnrjenkins .
docker build -t mrnrmaventest:3-jdk-8 .
docker build -t mrnrmavenprod:3-jd-8 .
docker build -t mrnrtomcat .
docker build -t mrnrmysql:5.5 .
```

## Run on host

Run your own jenkins and configure it

```bash
docker run -d --name MrnrJenkins  -v CDB_Volume:/cdb -v WAR:/cdb_war -v /var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker -p 8085:8080 mrnrjenkins
docker exec MrnrJenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

Configure jenkins jobs 

## Run in jenkins container

### Test job 

```bash
sudo cp -rf /var/jenkins_home/workspace/ComputerDatabase_test/ComputerDataBase/. /cdb
sudo rm -rf ./*
sudo docker run -d -it --name MrnrMysqlTest --network test_network --ip 172.18.0.6 mrnrmysql:5.5
sudo docker run --network test_network --ip 172.18.0.7 --name MrnrMavenTest -v CDB_Volume:/usr/src/app mrnrmaventest:3-jdk-8
```

### Prod job 

```bash
sudo docker stop MrnrMysqlTest MrnrMavenTest
sudo docker rm MrnrMysqlTest MrnrMavenTest

if [ ! "$(sudo docker ps -q -f name=MrnrTomcatProd)" ]; then
	sudo docker run -d --network prod_network --ip 172.20.0.5 -it -p 8888:8181 --name MrnrTomcatProd -v WAR:/usr/local/tomcat/webapps mrnrtomcat
fi

if [ ! "$(sudo docker ps -q -f name=MrnrMysqlProd)" ]; then
	sudo docker run -d -it --name MrnrMysqlProd --network prod_network --ip 172.20.0.2 mrnrmysql:5.5
fi

if [ ! "$(sudo docker ps -a -q -f name=MrnrMavenProd)" ]; then
	sudo docker run --network prod_network--ip 172.20.0.4 --name MrnrMavenProd -v CDB_Volume:/usr/src/app mrnrmavenprod:3-jdk-8
else 
	sudo docker start -i MrnrMavenProd
fi

sudo cp -a /cdb/target/ComputerDatabase-0.0.1-SNAPSHOT.war /cdb_war
sudo rm -rf /cdb/*
```

# Docker Cloud

## Setup des dockers

Use images from [maximernr](https://hub.docker.com/r/maximernr/ "MaximeRnr's docker hub") docker hub

Configure containers carefully : Volumes & Ports

Configure jenkins jobs 

## Run in jenkins container

Change container's ids by yours,
you can find them by using this command in jenkins bash.
```bash
sudo docker ps -a
```

### Test job 

```bash
sudo cp -rf /var/jenkins_home/workspace/ComputerDatabase_test/ComputerDataBase/. /cdb
sudo rm -rf ./*

if [ ! "$(sudo docker network inspect prod_network )" ]; then
	sudo docker network create --subnet 172.20.0.0/16 --gateway 172.20.0.1 --driver bridge prod_network
fi

if [ ! "$(sudo docker network inspect test_network )" ]; then
	sudo docker network create --subnet 172.21.0.0/16 --gateway 172.21.0.1 --driver bridge test_network
fi

#Maven
if [ "$(sudo docker ps -q -f id=bd2c16a29141 )" ]; then
	sudo docker stop bd2c16a29141
fi

#Mysql
if [ "$(sudo docker ps -q -f id=a39b2ba6ea58 )" ]; then
	sudo docker stop a39b2ba6ea58
fi

sudo docker start a39b2ba6ea58


#Mysql
sudo docker network disconnect test_network a39b2ba6ea58
sudo docker network connect --ip 172.21.0.6 test_network a39b2ba6ea58

#Maven
sudo docker network disconnect test_network bd2c16a29141
sudo docker network connect --ip 172.21.0.7 test_network bd2c16a29141

sudo docker start -i bd2c16a29141


#Mysql
sudo docker network disconnect test_network a39b2ba6ea58
sudo docker network connect --ip 172.21.0.6 test_network a39b2ba6ea58

#Maven
sudo docker network disconnect test_network bd2c16a29141
sudo docker network connect --ip 172.21.0.7 test_network bd2c16a29141

sudo docker start -i bd2c16a29141

```

### Prod job 

```bash
#Tomcat
sudo docker network disconnect prod_network 431dc7511b20 
sudo docker network connect --ip 172.20.0.5 prod_network 431dc7511b20 

if [ ! "$(sudo docker ps -q -f id=26ee93b31147)" ]; then
	sudo docker start 431dc7511b20
fi

#Mysql
sudo docker network disconnect prod_network 4e075fdf6f6d 
sudo docker network connect --ip 172.20.0.2 prod_network 4e075fdf6f6d 

if [ ! "$(sudo docker ps -q -f id=4e075fdf6f6d)" ]; then
	sudo docker start 4e075fdf6f6d
fi

#Maven
sudo docker network disconnect prod_network 48725ef1af53 
sudo docker network connect --ip 172.20.0.4 prod_network 48725ef1af53 

if [ ! "$(sudo docker ps -q -f id=48725ef1af53)" ]; then
	sudo docker start -i 48725ef1af53
fi


sudo cp -a /cdb/target/ComputerDatabase-0.0.1-SNAPSHOT.war /cdb_war
sudo mv /cdb_war/ComputerDatabase-0.0.1-SNAPSHOT /cdb_war/ComputerDatabase
sudo rm -rf /cdb/*
```



