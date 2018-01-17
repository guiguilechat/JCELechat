
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCommanderBattleCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersCommanderBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersCommanderBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  795;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersCommanderBattleCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidBloodRaidersCommanderBattleCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidBloodRaidersCommanderBattleCruiser> items;

    }

}
