
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCommanderCruiser.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneCommanderCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  845;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneCommanderCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidRogueDroneCommanderCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidRogueDroneCommanderCruiser> items;

    }

}
