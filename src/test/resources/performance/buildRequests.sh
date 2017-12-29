echo "How many request batches (50 XML and 50 JSON Requests per Batch)"
read T;
export PARAMETERS="idmuArchiveName=out"
counter=1
while [ $counter -le $T ]
do
 export PARAMETERS="$PARAMETERS&batches=$counter"
 ((counter++))
done
let "r = $T * 50"
let "t = $r / 2000"
echo Generating $r XML and $r JSON requests - eta $t seconds to tar file
