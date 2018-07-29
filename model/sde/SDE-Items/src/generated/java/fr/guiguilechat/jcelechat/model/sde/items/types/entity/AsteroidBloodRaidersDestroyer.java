package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidBloodRaidersDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersDestroyer.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  577;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidBloodRaidersDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidBloodRaidersDestroyer> items;
    }
}
