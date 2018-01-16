cp /Users/flatballflyer/GitHub/IBM-Data-Merge-Utility-REST/target/idmu-rest-4.0.1-SNAPSHOT.war ./war/
docker container stop idmu
docker container rm idmu
docker rmi idmu
docker build -t idmu .
docker run -d -p 9080:9080 -p 9443:9443 --name idmu idmu

