# JCELechat

Java Compiled Eve Library by Lechat
Eve Online database and service interaction.

This project is split in

## model

Contains the SDE and ESI specific project.

### SDE

Static Data Export is a set of files provided by CCP that contain a representation of static data of the game.
Typically this gives access to ship data.
The access uses static synchronized singleton classes, so loading the data several times should not impact performances - unless done in several classloaders. 

It is split in several modules.

#### SDE-Industry

This module gives access to blueprints, invention decryptors, and for each item, its uses in for industry.

#### SDE-Items

This modules compiles the categories and groups of items, then allows to load the items in the game .
The parsing of data is dynamically compiled.

example of code :

```java
// get an item by name.
Asteroid veld = (Asteroid) fr.guiguilechat.eveonline.model.sde.items.MetaInf.getItem("Veldspar");
veld.CompressionQuantityNeeded;
```

#### SDE-Items-compiler

Provide the functions to compile the SDE into items, and to translate the SE into corresponding classes ; then to save/load the created db.

This module is only supposed to be used during compilation phase of SDE-Items.

#### SDE-Locations

Give access to the stations, systems, constellations, and regions.
Also provides an algorithm to compute distances.

#### SDE-NPCs

List the NPC corporations, their agents and their LP offers.

#### SDE-Full

Contains the above mentioned modules.

#### SDE-Raw

This module is responsible for downloading, caching, and parsing CCP files.

You should not use it, it is not in dependency of other modules.

#### SDE-Tools

Contains functions that help the other modules, it's in dependency of other modules.

### ESI

The Eve Swagger Interface  gives access to the data of Eve through a web service.

This module proposes an implementation that gives access to the raw service.

#### ESI-compiler

compiles the Swagger.json from CCP and creates

 - the classes for to the required/returned structure
 - a Swagger interface that requires GET and POST implementation
 - the methods in the Swagger interface corresponding to the Swagger.json paths.
 
#### ESI

Uses ESI-Compiler to produce the Swagger ; Then implements the Swagger into an ESIConnection class.

Also provides methods to get your own ESI dev/client keys.

Then I also code methods to model the ESI in a less binary and more Object-oriented way. The goal is to have automated continuous retrieval of data, using javafx ObservableCollections and a cache management daemon.

This way, GUI that use data won't need to wait for the data to be fetched, but instead will get the collection once, iterate in a synchronized loop over its items, and still synchronized will add listeners to that collection, so as to be notified when the data are actually fetched.

## programs

Basically those drive the development of the ESI. My own personal programs that I used are removed from this, because they bring me isks and I don't want to waste my work.
