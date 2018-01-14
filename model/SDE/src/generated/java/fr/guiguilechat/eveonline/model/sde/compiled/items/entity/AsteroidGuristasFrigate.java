
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/AsteroidGuristasFrigate.yaml";
    private static LinkedHashMap<String, AsteroidGuristasFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  562;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasFrigate.class;
    }

    public static LinkedHashMap<String, AsteroidGuristasFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidGuristasFrigate> items;

    }

}
