# JCELechat

Java Compiled Eve Library by Lechat.

Eve Online database and service interaction.

This project contains auto-generated libraries to acces Eve Online's static data and dynamic data.
Static data is in the SDE sub project, dynamic is in the ESI sub project, though the ESI allows to acces static data too.

## Project installation

Once cloned, you SHOULD start the project with sh/install to download the required libs without errors about http repo

If you fork the project and you want to deploy it on your own repo, depending on how you deploy, you need to set the following values

### Github actions

your github project needs to be set the properties:

 - DOCKER_HUB_USER : contains your dockerhub username to export the docker images as. In my case, [glechat](https://hub.docker.com/u/glechat)
 - GH_REPO : contains the repo path within GH . In my case, [guiguilechat/JCELechat](https://github.com/guiguilechat/JCELechat)
 
 Also needs the secret : 
 
  - DOCKER_HUB_TOKEN : contains the AT to your gihub repo. You can create it [here](https://hub.docker.com/settings/security)
 
### manual deploy

your project needs the following values in your settings.xml :

```xml
<settings>
  <servers>
    <server>
        <id>github-jcelechat</id>
        <username>guiguilechat</username>
        <password>REDACTED</password>
    </server>
  </servers>
  <profiles>
    <profile>
      <id>dockerhubPAT_eveporoxy</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
        <EVEPROXY_DOCKER_HUB_TOKEN>REDACTED</EVEPROXY_DOCKER_HUB_TOKEN>
        <EVEPROXY_DOCKER_HUB_USER>glechat</EVEPROXY_DOCKER_HUB_USER>
        <GH_JCELECHAT>guiguilechat/JCELechat</GH_JCELECHAT>
      </properties>
    </profile>
  </profiles>
</settings>
```

Of course you need to update the values accordingly. In addition to the properties used for github actions, the github-jcelechat server requires the PAT as a password, which you can create [here](https://github.com/settings/tokens)

## model

Contains the SDE and ESI specific project.

### SDE

Static Data Export is a set of files provided by CCP that contain a representation of static data of the game.
Typically this gives access to ships, modules, blueprints, etc. data.

In those modules I give acces to the data in a java ish way. For this, programs compile the SDE in java classes, then translate the SDE data into items of those classes. Those items are then saved in the module resources directory, to be loaded on usage.

The libs are accesses through static synchronized singleton classes, so loading the data several times should not impact performances - unless done in several classloaders. 

It is split in several modules.

#### [SDE-Industry](model/sde/SDE-Industry)

This module gives access to blueprints, invention decryptors, and for each item, its uses in industry.

#### [SDE-Types](model/sde/SDE-Types)

This modules compiles the categories and groups of items, then allows to load the items in the game.

The parsing of data is dynamically compiled.

example of usage :

```java
// get an item by name.
Asteroid veld = (Asteroid) fr.guiguilechat.jcelechat.model.sde.TypeIndex.getType("Veldspar");
// access to the compression variable of veldspar.
veld.CompressionQuantityNeeded;
// get the types of the group bistot
fr.guiguilechat.jcelechat.model.sde.types.asteroid.Bistot.METAGROUP.load().values();
```
[here](model/sde/SDE-Types/src/test/java/fr/guiguilechat/jcelechat/sde/items/HighestBSBumper.java) is an example of how to find the BS with the most energy to bump a ship.

#### SDE-Types-compiler

Provide the functions to compile the SDE into types, and to translate the SE into corresponding classes ; then to save/load the created db.

This module is only supposed to be used during compilation phase of SDE-Types.

#### [SDE-Locations](model/sde/SDE-Locations)

Give access to the stations, systems, constellations, and regions.

Also provides an algorithm to compute distances.

Since it does not support the citadels, you should use the ESI instead.

#### [SDE-NPCs](model/sde/SDE-NPCs)

List the NPC corporations, their agents and their LP offers.

#### [SDE-Full](model/sde/SDE-Full)

Contains the above mentioned modules.

#### SDE-Raw

This module is responsible for downloading, caching, and parsing CCP files.

You should not use it, it is not required as dependency of other modules.

#### SDE-Tools

Contains functions that help the other modules, it's in dependency of other modules.

### [ESI](model/esi)

The Eve Swagger Interface  gives access to the data of Eve through a web service.

This module proposes an access to the raw service.

#### [ESI-compiler](model/esi/ESI-compiler)

compiles the Swagger.json from CCP and creates

 - the classes for to the required/returned structure
 - a Swagger interface that requires GET and POST implementation
 - the methods in the Swagger interface corresponding to the Swagger.json paths.
 - abstract cache classes for the methods that are generated (only GET ) 
 
 Basically you don't use this, it is used by the other module to build the ESI classes.
 
#### [ESI](model/esi/ESI)

Uses ESI-Compiler to produce the Swagger ; Then implements the Swagger into an ESIConnection class.

example of code. This is from [a test class](https://github.com/guiguilechat/JCELechat/blob/master/model/esi/JCESI/src/test/java/fr/guiguilechat/jcelechat/model/jcesi/ExampleStaticAccess.java) in the esi.

```java
 /** a static access does not need an account to retrieve data */
 ESIStatic stat = ESIStatic.INSTANCE;

 int theforge = 10000002;
 int veldspar = 1230;

 /**
  * get the history of veldspar, in The Forge.<br />
  * take the first (=random) value and get the average sale value
  */
 double veldsparAVG = stat.get_markets_history(theforge, veldspar, null)[0].average;

 /**
  * some access have pages, we can fetch all the pages using this
  * method<br />
  * get all the page of present BUY orders in TheForge for Veldpsar,
  */
 List<R_get_markets_region_id_orders> bos = stat
  .loadPages((p, h) -> stat.get_markets_orders(order_type.buy, p, theforge, veldspar, h), null);
 /** then you can get eg the maximum BO */
 double maxbo = bos.stream().mapToDouble(bo -> bo.price).max().getAsDouble();

 /**
  * The esi has a built in cache manager.<br />
  * The cache fetch the pages and put the data in the holder
  */
 ObservableList<R_get_markets_region_id_orders> cachebos = stat.cache.markets.orders(order_type.buy, theforge,
  veldspar);

 /** cache should return the same holder when given the same parameters */
 if (stat.cache.markets.orders(order_type.buy, theforge, veldspar) != cachebos) {
 	throw new RuntimeException();
 }

 /** wait for the data to be fetched at least once */
 stat.waitL(cachebos);

 /**
  * Cache data are refreshed as soon as the expiry date is reached. When
  * working with a cache, you must sync over the holder to avoid cache
  * corruption
  */
 synchronized (cachebos) {
 	maxbo = cachebos.stream().mapToDouble(bo -> bo.price).max().getAsDouble();
 }

 /** you can create bindings to keep extracted data fresh */
 DoubleBinding maxBOBinding = Bindings.createDoubleBinding(() -> {
 	synchronized (cachebos) {
  return cachebos.stream().mapToDouble(bo -> bo.price).max().getAsDouble();
 	}
 }, cachebos);

 /**
  * since this is tedious and error prone I bring some in the modeled class
  */
 ObservableDoubleValue maxBOObs = ESIAccess.INSTANCE.markets.getMarket(theforge).getBO(veldspar, 1);

 /**
  * ^You should use this one ^ Everybody loves maxBOObs.
  */
```

Also provides methods to get your own ESI dev/client keys.

Then I also code methods to model the ESI in a less binary and more Object-oriented way. The goal is to have automated continuous retrieval of data, using javafx ObservableCollections and a cache management daemon.

This way, GUI that use data won't need to wait for the data to be fetched, but instead will get the collection once, iterate in a synchronized loop over its items, and still synchronized will add listeners to that collection, so as to be notified when the data are actually fetched.

## programs

Basically those drive the development of the ESI. My own personal programs that I used are removed from this, because they bring me isks and I don't want to waste my work.

Still some part are present, eg to visually check if I can load data, make small interesting examples.
The interesting examples make use of javafx library to build GUI with scenebuilder . Basically I draw a GUI, I create a main that invokes it, and I create the controller that controls the GUI. Most of the work is in the controller - once the lib is done.

Typically I only show here the programs that are working standalone GUI, other programs are more complex and are tailored for specific analysis.

### [OreWorth](programs/OreWorth)

This program gets the price (sell, buy, and daily average) for all ores and divide it by the volume of the ore, then presents it into a GUI.

[The main class](programs/OreWorth/src/main/java/fr/guiguilechat/jcelechat/programs/oreworth/OreWorthController.java) is very small with less than 90 LoC. It function as as a PoC for both the ESI (to fetch the prices) and the SDE (to get the ores and filter interesting data). Plus it can be integrated in another GUI, as a javafx module.

## [Praisal](programs/Praisal)

This program needs a list of items, and make prices to buy them on given regional market.
It also features buyback comparison, with up to 2 buybacks configurable with volumic price and collateral tax, to get the value of the items for each buybacks.
Finally, it also give the volumic BO/AVG of the items, eg in order to know which items are worth bringing to sell later.

[The main class](programs/Praisal/src/main/java/fr/guiguilechat/jcelechat/programs/praisal/PraisalController.java) is a bit more complex than the Oreworth one, with 130 LoC, but it's because the GUI actually has more buttons ^^ .
