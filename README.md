# JCELechat

Java Compiled Eve Library by Lechat.

Eve Online database and service interaction.

This project contains auto-generated libraries to acces Eve Online's static data and dynamic data.
Static data is in the SDE sub project, dynamic is in the ESI sub project, though the ESI allows to acces static data too.

## base repo

To import the modules in your maven project, you need to add my repo (unless you mvn install the project). to this end add in your pom.xml :

```
	<repositories>
		<repository>
			<id>kimsufi-stable</id>
			<url>http://91.121.120.36/maven/stable</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
		<repository>
			<id>kimsufi-snapshots</id>
			<url>http://91.121.120.36/maven/snapshot/</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	</repositories>
```

## model

Contains the SDE and ESI specific project.

### SDE

Static Data Export is a set of files provided by CCP that contain a representation of static data of the game.
Typically this gives access to ships, modules, blueprints, etc. data.

In those modules I give acces to the data in a java ish way. For this, programs compile the SDE in java classes, then translate the SDE data into items of those classes. Those items are then saved in the module resources directory, to be loaded on usage.

The libs are accesses through static synchronized singleton classes, so loading the data several times should not impact performances - unless done in several classloaders. 

It is split in several modules.

#### SDE-Industry

This module gives access to blueprints, invention decryptors, and for each item, its uses in industry.

#### SDE-Items

This modules compiles the categories and groups of items, then allows to load the items in the game.

The parsing of data is dynamically compiled.

example of usage :

```java
// get an item by name.
Asteroid veld = (Asteroid) fr.guiguilechat.jcelechat.model.sde.items.MetaInf.getItem("Veldspar");
// access to the compression variable of veldspar.
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

You should not use it, it is not required as dependency of other modules.

#### SDE-Tools

Contains functions that help the other modules, it's in dependency of other modules.

### ESI

The Eve Swagger Interface  gives access to the data of Eve through a web service.

This module proposes an access to the raw service.

#### ESI-compiler

compiles the Swagger.json from CCP and creates

 - the classes for to the required/returned structure
 - a Swagger interface that requires GET and POST implementation
 - the methods in the Swagger interface corresponding to the Swagger.json paths.
 - abstract cache classes for the methods that are generated (only GET ) 
 
 Basically you don't use this, it is used by the other module to build the ESI classes.
 
#### ESI

Uses ESI-Compiler to produce the Swagger ; Then implements the Swagger into an ESIConnection class.

example of code. This is from a test class in the esi.
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

### OreWorth

[This program](programs/OreWorth) gets the price (sell, buy, and daily average) for all ores and divide it by the volume of the ore, then presents it into a GUI.

[The main class](programs/OreWorth/src/main/java/fr/guiguilechat/jcelechat/programs/oreworth/OreWorthController.java) is very small (142LOC) and functionas as a PoC for both the ESI (to fetch the prices) and the SDE (to get the ores and filter interesting data). Plus it can be integrated in another GUI, as a javafx module.
