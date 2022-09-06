
sql_dir=/container-entrypoint-initdb.d/sql/
for script in $(ls $sql_dir)
do
 echo "running script $script"
 start=`date +%s`
 SQLPATH=$sql_dir sqlplus -s "$APP_USER/$APP_USER_PASSWORD@//localhost/XEPDB1" "@$script"
 end=`date +%s`
 runtime=$((end-start))
 echo "ran $script in $runtime s"
done
