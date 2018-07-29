package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidSerpentisBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisBattleship.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  570;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisBattleship.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSerpentisBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSerpentisBattleship> items;
    }
}
