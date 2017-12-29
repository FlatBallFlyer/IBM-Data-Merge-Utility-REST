echo Cleanup from previous run
./cleanup.sh
source ./buildRequests.sh
./buildData.sh $1
./buildXml.sh $1
./buildJson.sh $1
./compareXml.sh 
./compareJson.sh

