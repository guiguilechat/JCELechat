FROM tomcat:10.1.15

RUN mv /usr/local/tomcat/webapps /usr/local/tomcat/webapps2
RUN mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps
run sed -i '/Valve className/d' /usr/local/tomcat/webapps/*/META-INF/context.xml
run sed -i '/allow=/d' /usr/local/tomcat/webapps/*/META-INF/context.xml
COPY tomcat-users.xml conf/