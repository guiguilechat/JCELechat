# SDE-Items

This is a module that allow to list the types in Eve Online, and get their information, in a static way.

## Usage

### Maven import

#### My repo

You need to add the mvn repo in your pom : 

```
  <repositories>
    <repository>
      <id>lechat-repo</id>
      <url>http://91.121.120.36/maven/stable</url>
      <snapshots><enabled>false</enabled></snapshots>
    </repository>
  </repositories>
```

Then you can specify the version, and set it as dependency :

#### Import as direct dependency

```
	<dependencies>
		<dependency>
			<groupId>fr.guiguilechat.jcelechat.model.sde</groupId>
			<artifactId>SDE-Items</artifactId>
			<version>${lechat.version}</version>
		</dependency>
	</dependencies>
```

However in a multi-project this can lead to issue, as you would need to update the version in each project.
In that case, it's better is to set it as a managed dependency in the root project, and use that dependency in the children.

#### Import as managed dependency

root project pom.xml : 

```
 <properties>
   <lechat.version>5.4.7</lechat.version>
 </properties>

 <dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>fr.guiguilechat.jcelechat.model.sde</groupId>
			<artifactId>SDE-Items</artifactId>
			<version>${lechat.version}</version>
		</dependency>
  </dependencies>
 </dependencyManagement>
```

each module that requires the prject as dependency : 

```
	<dependencies>
		<dependency>
			<groupId>fr.guiguilechat.jcelechat.model.sde</groupId>
			<artifactId>SDE-Items</artifactId>
		</dependency>
	</dependencies>
```

When you want to update the version, you just change the property in the root pom.

### Java Code

The code is available in the package [fr.guiguilechat.jcelechat.model.sde.items](src/generated/java/fr/guiguilechat/jcelechat/model/sde/items)

The main class is [ItemIndex](src/generated/java/fr/guiguilechat/jcelechat/model/sde/items/ItemIndex.java) that allows to resolve items, either by name, or by id. See [this test class](src/test/java/fr/guiguilechat/jcelechat/sde/items/TestLoad.java) for examples of doing so.
As shown in this test class, you can also load the metagroup and metacategories to list their items.

In [this test case](src/test/java/fr/guiguilechat/jcelechat/sde/items/HighestBSBumper.java) I load the group of the battleships, and then deduce their maximum energy ratio with a MWD to find out which is the best bumper.

In [this one](src/test/java/fr/guiguilechat/jcelechat/sde/items/ShipMaxSpeed.java) I evaluate the max speed of small ships, with a MWD if possible. I don't consider the T3D so it does not work ^^.


## Model

There are three levels for items : 
1. MetaCat is the category in Eve. eg the Drone Item is abstract, but its MetaCat Drone.METACAT allow to list the groups of this category.
2. MetaGroup is the group in Eve. eg the CombatDrone Item is abstract, extends Drone (so its meta cat is Drone.METACAT), proposes its group as CombatDrone.METAGROUP , which can be used to list all the items in this group, eg with CombatDrone.METAGROUP.load().

A specific item can be found by its id, or its name, using ItemIndex.getItem(search), which internally loads all the categories and all the groups. It's also possible to load all the items of a group, and all the groups of a category, using the metagroup/metacat load() calls. Typically if I want to load all the fighters I can do 
```java
  var allfighters=Figther.METACAT.groups.stream().flatMap(g-> g.load().values().stream()).collect(Collectors.toList())
```
