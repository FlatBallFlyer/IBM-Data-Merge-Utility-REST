echo "Process the json->xml transformations"
rm -rf ./perf/payloadFolder
mv ./perf/jsonSource ./perf/payloadFolder
rm -rf ./perf/output
mkdir ./perf/output

curl -X DELETE $1/Group/test > /dev/null 2> /dev/null
curl -X POST -d @./perf/templates/templates.json $1/Group > /dev/null 2> /dev/null

cd ./perf/payloadFolder
for f in ./*; do
  echo -e -n "\r $f    ";
  curl -X GET -d @./$f $1/Merge/test.aToB. > ../output/$f 2> /dev/null &
done
cd ../..
echo "";

rm -rf ./perf/jsonSource
mv ./perf/payloadFolder ./perf/jsonSource
rm -rf ./perf/xmlGenerated
mv ./perf/output ./perf/xmlGenerated

