
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCommanderDestroyer.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneCommanderDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  846;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneCommanderDestroyer.class;
    }

    public static LinkedHashMap<String, AsteroidRogueDroneCommanderDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCommanderDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidRogueDroneCommanderDestroyer> items;

    }

}
