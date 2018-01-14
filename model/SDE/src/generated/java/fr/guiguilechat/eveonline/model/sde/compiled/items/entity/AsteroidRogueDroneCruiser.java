
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/AsteroidRogueDroneCruiser.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  757;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidRogueDroneCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidRogueDroneCruiser> items;

    }

}
