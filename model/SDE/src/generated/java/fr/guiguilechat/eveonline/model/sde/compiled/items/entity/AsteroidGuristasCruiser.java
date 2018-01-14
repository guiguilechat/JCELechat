
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/AsteroidGuristasCruiser.yaml";
    private static LinkedHashMap<String, AsteroidGuristasCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  561;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidGuristasCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidGuristasCruiser> items;

    }

}
