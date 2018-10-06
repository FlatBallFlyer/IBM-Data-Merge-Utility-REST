docker tag idmu flatballflyer/idmu:latest
docker push flatballflyer/idmu:latest
docker stop idmu
docker rm idmu
docker run -d -p 9080:8080 --name idmu flatballflyer/idmu:latest

