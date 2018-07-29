package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidGuristasDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasDestroyer.yaml";
    private static LinkedHashMap<String, AsteroidGuristasDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  579;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidGuristasDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidGuristasDestroyer> items;
    }
}
