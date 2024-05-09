

## remote install

### Create postgres db

this will also store db password in app config file

```bash
PGPASSWORD=$(tr -dc 'A-Za-z0-9!"#$%&()*+,-./:;<=>?@[\]^_{|}~' </dev/urandom | head -c 32  ; echo)
sudo -u postgres psql\
 -c "create user mevetic with login PASSWORD '$PGPASSWORD';"\
 -c "create database mevetic with owner 'mevetic' encoding 'utf8';"\
 -c "grant all privileges on database mevetic to mevetic;"


sudo tee /etc/tomcat10/Catalina/localhost/MEvEtic.xml <<- EOF
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <Environment name="spring.config.location" value="file:/var/MEvEtic/" type="java.lang.String"/>
</Context>
EOF

sudo chgrp tomcat /etc/tomcat10/Catalina/localhost/MEvEtic.xml
sudo mkdir -p /var/MEvEtic

sudo tee /var/MEvEtic/application.properties << EOF
spring.datasource.password=$PGPASSWORD
spring.datasource.url=jdbc:postgresql://localhost:5432/mevetic
spring.datasource.username=mevetic
spring.jpa.hibernate.ddl-auto=update
#spring.jpa.properties.hibernate.generate_statistics=true
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.jdbc.fetch_size=50
spring.jpa.properties.hibernate.order_inserts=true

EOF

sudo chgrp -R tomcat /var/MEvEtic
```

Then you need to edit the /var/MEvEtic/application.properties to add your app information.

Let's say you have an app called "myapp", with a redirect [MEvEtic URL]/login/oauth2/code/myapp . The redirect MUST have this format.

The following will add a connection with only default public scope ; and another connection with more scopes, but specifies same redirect to use same app.

```
spring.security.oauth2.client.registration.myapp.clientId=[app id]
spring.security.oauth2.client.registration.myapp.clientName=empty access
spring.security.oauth2.client.registration.myapp.clientSecret=[app secret]
spring.security.oauth2.client.registration.myapp.provider=ccplz
spring.security.oauth2.client.registration.myapp.scope=publicData

spring.security.oauth2.client.registration.standings.clientId=[app id]
spring.security.oauth2.client.registration.standings.clientName=standings
spring.security.oauth2.client.registration.standings.clientSecret=[app secret]
spring.security.oauth2.client.registration.standings.provider=ccplz
spring.security.oauth2.client.registration.standings.redirectUri=[MEvEtic URL]/login/oauth2/code/myapp
spring.security.oauth2.client.registration.standings.scope=esi-characters.read_contacts.v1,esi-characters.read_standings.v1
```

If you have another dev app, you can also have another connection.

### Reset DB

```bash
sudo service tomcat10 stop
sudo -u postgres psql\
 -c "drop database mevetic"\
 -c "create database mevetic with owner 'mevetic' encoding 'utf8';"\
 -c "grant all privileges on database mevetic to mevetic;"
sudo service tomcat10 start
```