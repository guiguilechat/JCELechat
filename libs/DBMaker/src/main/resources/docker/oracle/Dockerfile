FROM gvenzl/oracle-xe:21.3.0-slim-faststart as dbhold


ENV APP_USER=lechat APP_USER_PASSWORD=lechat ORACLE_DATABASE=lechat_db ORACLE_PASSWORD=oracle
COPY --chown=oracle:dba ./sql/* /container-entrypoint-initdb.d/


RUN sed -i 's/  echo "DATABASE IS READY TO USE!"/exit 0/g' container-entrypoint.sh
USER root
RUN chmod a+rwx /container-entrypoint-initdb.d/*
USER oracle
RUN ["container-entrypoint.sh"]

FROM gvenzl/oracle-xe:21.3.0-slim-faststart

ENV APP_USER=lechat APP_USER_PASSWORD=lechat ORACLE_DATABASE=lechat_db ORACLE_PASSWORD=oracle
COPY --from=dbhold /opt/oracle/oradata/ /opt/oracle/oradata/