# Eve History

This program fetch Eve data and store the result, allowing historical analysis

## Installation on remote ubuntu server

You need a server with only required ssh access, and later http on 8080

### Install required programs

This will install java with a recent enough version, postgresql and tomcat

`sudo apt install -y openjdk-21-jdk openjdk-21-source postgresql-15 tomcat10`

### Configure

add yourself to groups.

```bash
sudo adduser $USER postgres # modification of postgres config
sudo adduser $USER tomcat # modification of postgres config
sudo adduser $USER adm # visualize logs
```

maybe you need to reconnect ssh to get granted the groups.

#### postgres

allow write from group postgres on /etc/postgresql and add a connexion rule, then restart postgresql

```bash
sudo chmod -R g+w /etc/postgresql/
sed -i '/local.*all.*postgres.*peer/a local   evehistory      evehistory                              scram-sha-256' /etc/postgresql/15/main/pg_hba.conf
sudo service postgresql restart
```

create a new random password, user & db

```bash
PGPASSWORD=$(tr -dc 'A-Za-z0-9!"#$%&()*+,-./:;<=>?@[\]^_{|}~' </dev/urandom | head -c 32  ; echo)
sudo -u postgres psql -c "create user evehistory with login PASSWORD '$PGPASSWORD';"
sudo -u postgres psql -c "create database evehistory with owner 'evehistory' encoding 'utf8';"
sudo -u postgres psql -c "grant all privileges on database evehistory to evehistory;"
```


Try to connect

```bash
PGPASSWORD=$PGPASSWORD  psql -U evehistory -d evehistory
```

This should give you a psql command as evehistory.

If you failed or have to reconnect, you can delete the user and start again from its creation with

```bash
sudo -u postgres psql -c "drop database evehistory"
sudo -u postgres psql -c "drop user evehistory"
```

#### Add a config in tomcat


This will make tomcat load a specific properties for the app

``` bash
sudo tee /etc/tomcat10/Catalina/localhost/EveHistory.xml <<- EOF
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Environment name="spring.config.location" value="file:/var/EveHistory/" type="java.lang.String"/>
</Context>
EOF

sudo chgrp tomcat /etc/tomcat10/Catalina/localhost/EveHistory.xml
sudo mkdir -p /var/EveHistory

sudo tee /var/EveHistory/application.properties << EOF
spring.datasource.password=$PGPASSWORD
spring.datasource.url=jdbc:postgresql://localhost:5432/evehistory
spring.datasource.username=evehistory
spring.jpa.hibernate.ddl-auto=update
#spring.jpa.properties.hibernate.generate_statistics=true
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.jdbc.fetch_size=50
spring.jpa.properties.hibernate.order_inserts=true
EOF

sudo chgrp -R tomcat /var/EveHistory

sudo service tomcat10 restart
```


### deploying, monitoring, access

#### deploy the war

first set your ssh connexion, replace its name in the *remote* file. If you have already set a host in your ~/.ssh/config then use that host name ; otherwise enter your user@url (like admin@127.0.0.1 )

I strongly advice to export your public key with eg `source remote; ssh-copy-id $RMT_SSH` . This will prevent to enter your password everytime.

Then run `sh/deployrmt`

#### monitor the remote logs

run the command `sh/logrmt`

#### check http access

go on <yourserveraddress>:8080/EveHistory/index
you should see "working"