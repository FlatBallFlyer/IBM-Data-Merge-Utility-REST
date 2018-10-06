cp /Users/flatballflyer/GitHub/IBM-Data-Merge-Utility-REST/target/idmu-rest-4.0.2.war ./war/
docker stop idmu
docker rm idmu
docker rmi idmu
docker build -t idmu .
docker tag idmu flatballflyer/idmu:latest
docker tag idmu flatballflyer/idmu:4.0.2
docker run -d -p 9080:8080 -p 9443:9443 --name idmu idmu

