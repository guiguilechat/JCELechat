FROM gvenzl/oracle-xe:21.3.0-slim-faststart 

ENV ORACLE_PASSWORD=oracle APP_USER=lechat APP_USER_PASSWORD=lechat

COPY --chown=oracle:dba ./sql/ /container-entrypoint-initdb.d/sql/
COPY --chown=oracle:dba ./import_db.sh /container-entrypoint-initdb.d/

RUN cp container-entrypoint.sh container-entrypoint.sh.bak && sed -i 's/  echo "DATABASE IS READY TO USE!"/exit 0/g' container-entrypoint.sh
USER root
RUN chmod a+rwx /container-entrypoint-initdb.d/*
USER oracle
RUN ["container-entrypoint.sh"]
USER root
RUN rm container-entrypoint.sh && mv container-entrypoint.sh.bak container-entrypoint.sh
USER oracle
