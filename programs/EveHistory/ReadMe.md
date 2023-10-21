# Eve History

This program fetch Eve data and store the result, allowing historical analysis

## Installation on remote ubuntu server

You need a server with only required ssh access, and later http on 8080

### Install required programs

This will install java with a recent enough version, postgresql and tomcat

`sudo apt install -y openjdk-21-jdk openjdk-21-source postgresql-15 tomcat10`

### Configure


#### postgres

create a new random password, user & db

```bash
PGPASSWORD=$(tr -dc 'A-Za-z0-9!"#$%&()*+,-./:;<=>?@[\]^_{|}~' </dev/urandom | head -c 32  ; echo)
sudo -u postgres psql -c "create user evehistory with login PASSWORD '$PGPASSWORD';"
sudo -u postgres psql -c "create database evehistory with owner 'evehistory' encoding 'utf8';"
sudo -u postgres psql -c "grant all privileges on database evehistory to evehistory;"
```

add a connexion rule 

```bash
sed -i '/local.*all.*postgres.*peer/a local   evehistory      evehistory                              scram-sha-256' /etc/postgresql/15/main/pg_hba.conf
```

restart postgres and try to connect

```bash
sudo service postgresql restart
PGPASSWORD=$PGPASSWORD  psql -U evehistory -d evehistory
```

This should give you a psql command as evehistory

#### Add a config in tomcat

This will make tomcat load a specific properties for the app

``` bash
sudo cat > /etc/tomcat10/Catalina/localhost/EveHistory.xml <<- EOF
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Environment name="spring.config.location" value="file:/var/EveHistory/" type="java.lang.String"/>
</Context>
EOF

sudo chgrp tomcat /etc/tomcat10/Catalina/localhost/EveHistory.xml
sudo mkdir /var/EveHistory

sudo cat > /var/EveHistory/application.properties << EOF
spring.datasource.password=$PGPASSWORD
spring.datasource.url=jdbc:postgresql://localhost:5432/evehistory
spring.datasource.username=evehistory
spring.jpa.hibernate.ddl-auto=update
EOF

sudo chgrp -R tomcat /var/EveHistory
```

Add yourself to group tomcat (to allow scp ) and adm (to allow logs vizualisation)

```
sudo adduser $USER tomcat
sudo adduser $USER adm
```

### deploying, monitoring, access

#### deploy the war

first set your ssh connexion , replace its name in the *remote* file.
Then run `sh/deployrmt`

#### monitor the remote logs

run the command `sh/logrmt`

#### check http access

go on <yourserveraddress>:8080/EveHistory/index
you should see "working"