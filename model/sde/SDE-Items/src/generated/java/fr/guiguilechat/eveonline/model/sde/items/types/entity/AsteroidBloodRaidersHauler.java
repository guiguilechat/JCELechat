package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersHauler
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersHauler.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersHauler> cache = (null);

    @Override
    public int getGroupId() {
        return  558;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersHauler.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidBloodRaidersHauler> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersHauler.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidBloodRaidersHauler> items;
    }
}
