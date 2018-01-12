docker container stop idmu
docker container rm idmu
docker rmi idmu
docker build -t idmu .
docker run -d -p 9080:9080 -p 9443:9443 --name idmu idmu

