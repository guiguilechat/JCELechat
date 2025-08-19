# No Scrape Eve

Keep a DB up-to-date using ESI

## File DB

To create a local database file in local `db/` dir.

```bash
mkdir db
docker run -d --rm --pull=always\
 -e SPRING_DATASOURCE_URL=jdbc:h2:/db/export_db\
 -v ./db:/db/\
 --name noscrape glechat/noscrapeve:latest
```

## Connecting to a DBMS

### Remote Postgresql

Generic (with variables) call.

```bash
docker run -d --rm --pull=always\
 -e SPRING_DATASOURCE_URL=jdbc:postgresql://[dburl]:[dbport]/[dbname]\
 -e SPRING_DATASOURCE_USERNAME=[dbusername]\
 -e SPRING_DATASOURCE_PASSWORD=[dbpassword]\
 --name noscrape glechat/noscrapeve:latest
```

### Local Postgresql

Example to only list, fetch and update the types (not the groups, not the attributes) on a local postgresql db, and stop the server when all the entities are up to date.

```bash
docker run --network=host --rm --pull=always\
	-e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/[dbname]\
 	-e SPRING_DATASOURCE_USERNAME=[dbusername]\
	-e SPRING_DATASOURCE_PASSWORD=[dbdbpassword]\
	-e JCESI_MANAGER_DEFAULT_SKIP=true\
	-e ESI_ITEMS_TYPE_UPDATE_SKIP=false\
	-e JCELECHAT_NOSCRAPE_ONCOMPLETE_STOP=true\
	-e LOGGING_LEVEL_FR_GUIGUILECHAT_JCELECHAT_LIBS=debug\
	-e LOGGING_LEVEL_FR_GUIGUILECHAT_JCELECHAT_PROGRAMS=trace\
	glechat/noscrapeve:latest
```

## Parameters

spring base params (eg parrallelism, db access, etc) are avail.  
also JCELechat specific, like 

```bash

ESI_ITEMS_TYPE_LIST_SKIP=true # do not discover new types from the listing endpoint
ESI_ITEMS_ATTRIBUTE_UPDATE_SKIP=true # disable the update of the attributes
ESI_ITEMS_TYPE_UPDATE_MAX=500        # only fetch 500 types per cycle
JCESI_MANAGER_PERIOD=20000           # at least 20s between two fetch cycle start
JCESI_MANAGER_DEFAULT_SKIP=false     # services that are not explicitly set to be skipped will fetch their data
LOGGING_FR_GUIGUILECHAT_JCELECHAT_LIBS_SPRING_UPDATE=DEBUG # debug updater : which services are started, fetching
```
