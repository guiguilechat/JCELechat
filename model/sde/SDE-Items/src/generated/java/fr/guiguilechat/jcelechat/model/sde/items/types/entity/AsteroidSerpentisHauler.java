package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisHauler
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisHauler.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisHauler> cache = (null);

    @Override
    public int getGroupId() {
        return  573;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisHauler.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSerpentisHauler> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisHauler.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSerpentisHauler> items;
    }
}
