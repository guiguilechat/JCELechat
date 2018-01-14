
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/AsteroidRogueDroneFrigate.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  759;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneFrigate.class;
    }

    public static LinkedHashMap<String, AsteroidRogueDroneFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidRogueDroneFrigate> items;

    }

}
