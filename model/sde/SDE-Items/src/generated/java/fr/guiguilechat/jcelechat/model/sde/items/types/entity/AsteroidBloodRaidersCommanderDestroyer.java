package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidBloodRaidersCommanderDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersCommanderDestroyer.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersCommanderDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  796;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersCommanderDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidBloodRaidersCommanderDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersCommanderDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidBloodRaidersCommanderDestroyer> items;
    }
}
