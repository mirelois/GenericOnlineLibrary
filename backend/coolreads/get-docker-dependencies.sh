docker pull postgres:16

docker pull openjdk:21-jdk

docker pull redis

mvn clean install -DskipTests=true

docker build -t coolreads .

