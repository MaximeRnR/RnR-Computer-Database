# RnR-Computer-Database

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


### Docker 

## Setup des dockers

docker network create network_cdb

docker volume create --name WAR
docker volume create --name CDB_Volume

docker build -t mrnrjenkins .
docker build -t mrnrmaven:3-jdk-8 .
docker build -t mrnrtomcat .

## RUN

docker run -d -it --name MrnrMysql --network network_cdb --ip 172.18.0.2 mrnrmysql
docker run -d --name MrnrJenkins --network network_cdb --ip 172.18.0.3  -v CDB_Volume:/cdb -v WAR:/cdb_war -v /var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker -p 8085:8080 mrnrjenkins
docker exec Mrnrjenkins cat /var/jenkins_home/secrets/initialAdminPassword
docker run --network=network_cdb --network network_cdb --ip 172.18.0.4 --name MrnrMaven -v CDB_Volume:/usr/src/app mrnrmaven
docker run --network=network_cdb --ip 172.18.0.5 -it -p 8888:8181 --name MrnrTomcat -v WAR:/usr/local/tomcat/webapps mrnrtomcat


## Dans le build Jenkins 

sudo cp -rf /var/jenkins_home/workspace/ComputerDatabase/ComputerDataBase/. /cdb
sudo rm -rf ./*
sudo docker start -i MrnrMaven
sudo cp -a /cdb/target/ComputerDatabase-0.0.1-SNAPSHOT.war /cdb_war
sudo rm -rf /cdb/*

