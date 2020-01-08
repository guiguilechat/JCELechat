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

The main class is ItemIndex that allows to resolve items, either by name, or by id. See src/test/java/fr/guiguilechat/jcelechat/sde/items/TestLoad.java for examples of doing so.

