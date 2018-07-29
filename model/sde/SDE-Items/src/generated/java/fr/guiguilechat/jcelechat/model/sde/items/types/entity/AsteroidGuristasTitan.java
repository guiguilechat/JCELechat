package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidGuristasTitan
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasTitan.yaml";
    private static LinkedHashMap<String, AsteroidGuristasTitan> cache = (null);

    @Override
    public int getGroupId() {
        return  1686;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasTitan.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidGuristasTitan> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasTitan.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidGuristasTitan> items;
    }
}
