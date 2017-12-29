echo Now Generating a tar with XML and JSON requests in it 

curl -X DELETE $1/Group/test 
curl -X POST -d @./generatePerfData/templates/templates.json $1/Group 
 
curl -X GET -d @./generatePerfData/payloadFolder/payload.txt $1/Merge/test..?$PARAMETERS 
curl -X GET -O $1/Archive/out.tar 

echo Extract the json testing data
cd perf/jsonSource
tar -xvf ../../out.tar *.json > /dev/null 2> /dev/null
cd ../..

echo Extract the xml testing data
cd perf/xmlSource
tar -xvf ../../out.tar *.xml > /dev/null 2> /dev/null
cd ../..


