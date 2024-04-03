# Eve spring programs

Each program follows some structure or behavior

## Project structure

### Scripts

They are in sh/ dir, then grouped by target environment

 - no env scripts (like deploy) are in the root ( sh/ )
 - docker scripts are in sh/dk/
   - there are scripts to manage the docker containers, and deploy the program in them, as well as show the logs
 - remote server scripts are in sh/rmt
   - they need a specific settings (depending on the program)
   - there are scripts to deploy the program in the remote server, and show the logs.


### Docker images

They should be in docker/ dir. The docker/compose contains several yml file, each dedicated for a set of services.

## Run the program in a server

Each Eve spring program can either be started in a servlet container (typically tomcat), or as a docker container.

### Run as tomcat

you need to set up the remote server informations.



### Run as docker container
