docker tag idmu flatballflyer/idmu:latest
docker push flatballflyer/idmu:latest
docker run -d -p 9080:9080 -p 9443:9443 --name idmu flatballflyer/idmu:latest
