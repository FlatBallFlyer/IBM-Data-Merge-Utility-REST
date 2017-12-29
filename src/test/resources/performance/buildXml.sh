echo "Process the xml->json transformations"
rm -rf ./perf/payloadFolder
mv ./perf/xmlSource ./perf/payloadFolder
rm -rf ./perf/output
mkdir ./perf/output

curl -X DELETE $1/Group/test > /dev/null 2> /dev/null
curl -X POST -d @./perf/templates/templates.json $1/Group > /dev/null 2> /dev/null

ls ./perf/payloadFolder/ | xargs -P 2 -n 1 -I FILE curl -X GET -d @./perf/payloadFolder/FILE $1/Merge/test.bToA. > ./perf/output/FILE 2> /dev/null

rm -rf ./perf/xmlSource
mv ./perf/payloadFolder ./perf/xmlSource
rm -rf ./perf/jsonGenerated
mv ./perf/output ./perf/jsonGenerated

