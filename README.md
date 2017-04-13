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


# Docker 

## Setup des dockers

docker network create network_cdb

docker volume create --name WAR
docker volume create --name CDB_Volume

docker build -t mrnrjenkins .
docker build -t mrnrmaventest:3-jdk-8 .
docker build -t mrnrmavenprod:3-jd-8 .
docker build -t mrnrtomcat .

## Run on host

Run your own jenkins and configure it

```bash
docker run -d --name MrnrJenkins --network network_cdb --ip 172.18.0.3  -v CDB_Volume:/cdb -v WAR:/cdb_war -v /var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker -p 8085:8080 mrnrjenkins
docker exec Mrnrjenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

Configure jenkins jobs 

## Run in jenkins container

### Test job 

```bash
sudo cp -rf /var/jenkins_home/workspace/ComputerDatabase_test/ComputerDataBase/. /cdb
sudo rm -rf ./*
sudo docker run -d -it --name MrnrMysqlTest --network network_cdb --ip 172.18.0.6 mrnrmysql:5.5
sudo docker run --network network_cdb --ip 172.18.0.7 --name MrnrMavenTest -v CDB_Volume:/usr/src/app mrnrmaventest:3-jdk-8
```

### Prod job 

```bash
sudo docker stop MrnrMysqlTest MrnrMavenTest
sudo docker rm MrnrMysqlTest MrnrMavenTest

if [ ! "$(sudo docker ps -q -f name=MrnrTomcatProd)" ]; then
	sudo docker run -d --network=network_cdb --ip 172.18.0.5 -it -p 8888:8181 --name MrnrTomcatProd -v WAR:/usr/local/tomcat/webapps mrnrtomcat
fi

if [ ! "$(sudo docker ps -q -f name=MrnrMysqlProd)" ]; then
	sudo docker run -d -it --name MrnrMysqlProd --network network_cdb --ip 172.18.0.2 mrnrmysql:5.5
fi

if [ ! "$(sudo docker ps -a -q -f name=MrnrMavenProd)" ]; then
	sudo docker run --network network_cdb --ip 172.18.0.4 --name MrnrMavenProd -v CDB_Volume:/usr/src/app mrnrmavenprod:3-jdk-8
else 
	sudo docker start -i MrnrMavenProd
fi

sudo cp -a /cdb/target/ComputerDatabase-0.0.1-SNAPSHOT.war /cdb_war
sudo rm -rf /cdb/*
```

