
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneSwarm
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneSwarm.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneSwarm> cache = (null);

    @Override
    public int getGroupId() {
        return  761;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneSwarm.class;
    }

    public static LinkedHashMap<String, AsteroidRogueDroneSwarm> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneSwarm.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidRogueDroneSwarm> items;

    }

}
