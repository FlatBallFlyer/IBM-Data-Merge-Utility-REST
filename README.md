# IBM Data Merge Utility v4.0.0 - REST

Overview

### NOW IN BETA!
IDMU-REST exposes the IBM Data Merge Utility as a set of Restful Services

## Related Pages
1. [IDMU Project](https://github.com/FlatBallFlyer/IBM-Data-Merge-Utility-REST) - Core IDMU Project
1. [IDMU Users Guide](https://flatballflyer.github.io/IBM-Data-Merge-Utility/USERS_GUIDE)
1. [IDMU-CLI Project](https://github.com/FlatBallFlyer/IBM-Data-Merge-Utility-CLI) - Command Line merge tool

### Fast Start
```
docker run -d -p 9080:9080 -p 9443:9443 --name idmu flatballflyer/idmu:latest
sleep 15
curl localhost:9080/idmu/Group
```
The server may take a few seconds to come up, if the curl command doesn't return this, check the "docker logs idmu"
```
[
  "system",
  "intro"
]
```
To access and run samples (requires git)
```
git clone https://github.com/FlatBallFlyer/IBM-Data-Merge-Utility-REST.git
cd IBM-Data-Merge-Utility-REST/src/test/resources/performance/
./testPerformance.sh localhost:9080/idmu
```
This should execute a series of scripts that load and execute merges. 
**Still Testing, Some errors during validation are expected**  

- see src/test/resources/performance/readme for an overview these tests

### See Also
1. [Releases](https://github.com/FlatBallFlyer/IBM-Data-Merge-Utility-REST/releases)
1. [IDMU Project](https://github.com/FlatBallFlyer/IBM-Data-Merge-Utility)
1. [Project Swagger](https://github.com/FlatBallFlyer/IBM-Data-Merge-Utility-REST/blob/master/idmu-rest.yaml)
1. [Template Json Schema](https://github.com/FlatBallFlyer/IBM-Data-Merge-Utility/blob/master/WebContent/jsonSchema/schema.template.json)

