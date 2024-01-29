# Eve Proxy

This program uses various Eve libs that fetch and store data into a DB to present them as an API

## Installation as docker container

An [example docker compose](docker/compose) is provided to start both the tomcat server and a postgresql server.
The version is fixed so you should modify it to have last eveproxy version.

You can start it using 

```bash
curl https://raw.githubusercontent.com/guiguilechat/JCELechat/master/programs/spring/eveproxy/docker/compose/postgres-tomcat.yml -o eveproxy.compose.yaml
docker compose -f eveproxy.compose.yaml up -d
```

Then use `docker logs eveproxy-tomcat` to have logs, and access it on local host 8080 port.

To stop it use

```bash
docker stop eveproxy-tomcat eveproxy-postgres
```

To purge it (to start them later or not)

```bash
docker container rm eveproxy-tomcat eveproxy-postgres
```

## Installation of working env

The working env is different because the binary files can be changed without the need to create a new tag : the docker container allows to have same env to execute, but the image is minimal to allow new updates.

Before you can run it, you need to have installed the whole project (mvn install at the root) .

The command `sh/dk/strt` creates a local docker installation, with tomcat on port 58080.

The command `sh/dk/dpl` then creates a war and deply it inside.

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

optional : open access from outside (may not work)

```bash
sed -i "s/#listen_addresses = 'localhost'/listen_addresses = '*'/" /etc/postgresql/15/main/postgresql.conf
sed -i 's$host    all             all             127.0.0.1/32$host    all             all             0.0.0.0/0$' /etc/postgresql/15/main/pg_hba.conf
```

allow write from group postgres on /etc/postgresql and add a connexion rule, then restart postgresql

```bash
sudo chmod -R g+w /etc/postgresql/
sed -i '/local.*all.*postgres.*peer/a local   eveproxy      eveproxy                              scram-sha-256' /etc/postgresql/15/main/pg_hba.conf
sudo service postgresql restart
```

create a new random password, user & db

```bash
PGPASSWORD=$(tr -dc 'A-Za-z0-9!"#$%&()*+,-./:;<=>?@[\]^_{|}~' </dev/urandom | head -c 32  ; echo)
sudo -u postgres psql -c "create user eveproxy with login PASSWORD '$PGPASSWORD';"
sudo -u postgres psql -c "create database eveproxy with owner 'eveproxy' encoding 'utf8';"
sudo -u postgres psql -c "grant all privileges on database eveproxy to eveproxy;"
```


Try to connect

```bash
PGPASSWORD=$PGPASSWORD  psql -U eveproxy -d eveproxy
```

This should give you a psql command as evehistory.

If you failed or have to reconnect, you can delete the user and start again from its creation with

```bash
sudo -u postgres psql -c "drop database eveproxy"
sudo -u postgres psql -c "drop user eveproxy"
```

#### Add a config in tomcat


This will make tomcat load a specific properties for the app.
This is only important if your tomcat is shared. Otherwise see next section


``` bash
sudo tee /etc/tomcat10/Catalina/localhost/EveProxy.xml <<- EOF
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Environment name="spring.config.location" value="file:/var/EveProxy/" type="java.lang.String"/>
</Context>
EOF

sudo chgrp tomcat /etc/tomcat10/Catalina/localhost/EveProxy.xml
sudo mkdir -p /var/EveProxy

sudo tee /var/EveProxy/application.properties << EOF
spring.datasource.password=$PGPASSWORD
spring.datasource.url=jdbc:postgresql://localhost:5432/eveproxy
spring.datasource.username=eveproxy
spring.jpa.hibernate.ddl-auto=update
#spring.jpa.properties.hibernate.generate_statistics=true
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.jdbc.fetch_size=50
spring.jpa.properties.hibernate.order_inserts=true

# market features
# fetch market every 2 min 
#market.updater.fetchperiod:120000
# fetch 100 histories at once
market.history.fetchsize=100
# fetch history every 22s
market.history.fetchperiod=22000

# SDE features
# force SDE update every cycle, used for debug
#sde.updater.forcereinsert=true
# check for new SDE once per minute.
#sde.updater.fetchperiod=60000

EOF

sudo chgrp -R tomcat /var/EveProxy
```

Then you need to reload tomcat

```
sudo service tomcat10 restart
```

#### Using environment variables

SpringBoot loads the environment variables as properties. So in order to specify the properties, set the env variables with a UPPER_UNDERSCORE casing, like `spring.jpa.hibernate.ddl-auto=update` becomes `SPRING_JPA_HIBERNATE_DDL_AUTO=update` . See for example the docker-compose provided.

### deploying, monitoring, access

Deployment is done on a remote server, so you need an SSH access to it.

#### set up remote access

You need to create a *$HOME/.cfg/EveHistory/remote.cfg* to store your ssh remote name . Typically copy the existing one 

``` bash
mkdir -p $HOME/.cfg/EveProxy/
cp remote.cfg $HOME/.cfg/EveProxy/remote.cfg
```

 then edit it with the correct values inside.

#### deploy the war

first set your ssh connexion, replace its name in the *$HOME/.cfg/EveHistory/remote.cfg* file. If you have already set a host in your ~/.ssh/config then use that host name ; otherwise enter your user@url (like admin@127.0.0.1 )

I strongly advice to export your public key with eg `source $HOME/.cfg/EveHistory/remote.cfg; ssh-copy-id $RMT_SSH` . This will prevent to enter your password everytime.

You need to have installed the whole project before deploying.

Then run `sh/rmt/dpl` to build and deploy the app remotely.

#### monitor the remote logs

run the command `sh/rmt/log` for last 1000 , or `sh/rmt/logFl` for all the logs in the last day.


#### check http access

go on <yourserveraddress>:8080/EveProxy/index
you should see "working"

#### reset remote DB

```bash
sudo service tomcat10 stop
sudo -u postgres psql -c "drop database eveproxy"
sudo -u postgres psql -c "create database eveproxy with owner 'eveproxy' encoding 'utf8';"
sudo -u postgres psql -c "grant all privileges on database eveproxy to eveproxy;"
sudo service tomcat10 start
```

