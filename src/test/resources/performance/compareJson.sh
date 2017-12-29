echo compair generated Json with source Json
for f in ./perf/jsonGenerated/*; do 
  echo -e -n "\r $f    ";
  diff "$f" "`echo $f | sed s/.xml/.json/ | sed s/jsonGenerated/jsonSource/`"; 
done
echo
