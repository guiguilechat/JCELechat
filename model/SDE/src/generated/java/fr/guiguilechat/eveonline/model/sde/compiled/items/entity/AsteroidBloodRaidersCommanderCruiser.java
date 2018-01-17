
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCommanderCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersCommanderCruiser.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersCommanderCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  791;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersCommanderCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidBloodRaidersCommanderCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidBloodRaidersCommanderCruiser> items;

    }

}
