
sql_dir=/container-entrypoint-initdb.d/sql/
for script in $(ls $sql_dir)
do
 SQLPATH=$sql_dir sqlplus -s "$APP_USER/$APP_USER_PASSWORD@//localhost/XEPDB1" "@$script"
done
