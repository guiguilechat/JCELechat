# Eve Proxy

This program uses various Eve libs that fetch and store data into a DB to present them as an API / HTML.

## Installation from docker compose

The [example docker compose](docker/compose) contains exampless of starting configurations.

The [postgres](docker/compose/eveproxy-postgres.yml) starts a tomcat server and a postgresql server.
You can start it using  (the pull command is to update the lastest version)  :

```bash
curl https://raw.githubusercontent.com/guiguilechat/JCELechat/master/programs/spring/eveproxy/docker/compose/eveproxy-postgresql.yml -o eveproxy-postgresql.yml
docker compose -f eveproxy-postgresql.yml pull
docker compose -f eveproxy-postgresql.yml up -d
```

Once started, to have the logs :

```bash
docker logs -f eveproxy-postgres-server
```

http access is on [local host port 8080](http://localhost:8080).

To stop it, but not delete it so that you can use the DB quickly later, call

```bash
docker stop eveproxy-postgres-db eveproxy-postgres-server
```

To purge it (so stop it, and remove all the associated files)

```bash
docker container rm -f eveproxy-postgres-db eveproxy-postgres-server
```

Another dockerfile in the same directory contains also a redis instance to delegate the cache to this server. This gives more control over the cache (I guess ?)

## Installation on local dev env

The dev env allows to deploy locally the image that you just created, typically to test modification on the classes.

This env is automatically updated by maven when the "install" goal is called (eg install, deploy). Therefore to update the local env, you can either run `mvn install` in that directory, or `sh/install` in the root module directory to bundle the modifications added in other modules.

Then to start it, a script [`sh/dk`](sh/dk) is present.

## Installation on remote ubuntu server

You need a server with only required ssh access, and later http on 8080

### Install required programs

This will install java with a recent enough version, postgresql and tomcat

`sudo apt install -y openjdk-21-jdk openjdk-21-source postgresql-15 tomcat10`

### Configure env

add yourself to groups.

```bash
sudo adduser $USER postgres # modification of postgres config
sudo adduser $USER tomcat # modification of tomcat config
sudo adduser $USER adm # visualize logs
```

maybe you need to reconnect ssh to get granted the groups.

### postgres

#### Access from outside

This is optional, as used solely for direct DB connetion.

```bash
sed -i "s/#listen_addresses = 'localhost'/listen_addresses = '*'/" /etc/postgresql/15/main/postgresql.conf
sed -i 's$host    all             all             127.0.0.1/32$host    all             all             0.0.0.0/0$' /etc/postgresql/15/main/pg_hba.conf
```

#### Allow password access

The default access is as unix socket only. This will grant access using password.

first, allow write from group postgres on /etc/postgresql ; second add a connexion rule ; then restart postgresql

```bash
sudo chmod -R g+w /etc/postgresql/
sed -i '/local.*all.*postgres.*peer/a local   eveproxy      eveproxy                              scram-sha-256' /etc/postgresql/15/main/pg_hba.conf
sudo service postgresql restart
```

#### Create user

create a new random password, user & db

```bash
PGPASSWORD=$(tr -dc 'A-Za-z0-9!"#$%&()*+,-./:;<=>?@[\]^_{|}~' </dev/urandom | head -c 32  ; echo)
sudo -u postgres psql\
 -c "create user eveproxy with login PASSWORD '$PGPASSWORD';"\
 -c "create database eveproxy with owner 'eveproxy' encoding 'utf8';"\
 -c "grant all privileges on database eveproxy to eveproxy;"
```

Try to connect

```bash
PGPASSWORD=$PGPASSWORD  psql -U eveproxy -d eveproxy
```

This should give you a psql command as eveproxy.

#### Delete user and DB

If you failed or have to reconnect, you can delete the user and start again from its creation with

```bash
sudo -u postgres psql -c "drop database eveproxy"
sudo -u postgres psql -c "drop user eveproxy"
```

### Tomcat

This will make tomcat load a specific properties for the app.

You can then modify that file to 

``` bash
sudo tee /etc/tomcat10/Catalina/localhost/EveProxy.xml <<- EOF
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Environment name="spring.config.location" value="file:/var/EveProxy/" type="java.lang.String"/>
</Context>
EOF

sudo chgrp tomcat /etc/tomcat10/Catalina/localhost/EveProxy.xml
sudo mkdir -p /var/EveProxy

sudo tee /var/EveProxy/application.properties>> /dev/null << EOF
logging.level.fr.guiguilechat.jcelechat.jcesi=info
logging.level.fr.guiguilechat.jcelechat.libs.spring=debug
logging.level.fr.guiguilechat.jcelechat.programs=trace

spring.cache.type=simple
spring.datasource.password=$PGPASSWORD
spring.datasource.url=jdbc:postgresql://localhost:5432/eveproxy?reWriteBatchedInserts=true
spring.datasource.username=eveproxy
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

You need to create a *$HOME/.cfg/EveProxy/remote.cfg* to store your ssh remote name. The script `sh/rmt/setup` does it, then request you to edit it.

Set your ssh connexion in that file. If you have already set a host in your ~/.ssh/config then use that host name ; otherwise enter your user@url (like admin@127.0.0.1 )

#### deploy the war

I strongly advice to export your public key with eg `source $HOME/.cfg/EveHistory/remote.cfg; ssh-copy-id $RMT_SSH` . This will prevent to enter your password everytime.

You need to have installed the whole project before deploying (`sh/install` at the root module).

Then run `sh/rmt/dpl` to build and deploy the app remotely.

#### monitor the remote logs

run the command `sh/rmt/log200` for last 200.

If there is none, use your system's logs to find why . Typically `source $HOME/.config/EveProxy/remote.cfg && ssh $RMT_SSH journalctl -n 1000 -u tomcat10.service`


#### check http access

go on <yourserveraddress>:8080/EveProxy/index
you should see "working"

### reset remote DB

If you need to reset the DB, you can clean it completely with

```bash
sudo service tomcat10 stop
sudo -u postgres psql -c "drop database eveproxy"
sudo -u postgres psql -c "create database eveproxy with owner 'eveproxy' encoding 'utf8';"
sudo -u postgres psql -c "grant all privileges on database eveproxy to eveproxy;"
sudo service tomcat10 start
```

