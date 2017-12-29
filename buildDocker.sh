cd src/main/docker
cp ../../../target/idmu-rest-4.0.1-SNAPSHOT.war .
docker container stop idmu
docker container rm idmu
docker build -t idmu .
docker run -d --name idmu -p 9080:9080 -p 9443:9443 idmu
