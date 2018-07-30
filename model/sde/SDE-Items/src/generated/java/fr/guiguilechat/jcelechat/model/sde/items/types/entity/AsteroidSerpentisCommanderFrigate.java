package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCommanderFrigate.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisCommanderFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  814;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisCommanderFrigate.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSerpentisCommanderFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSerpentisCommanderFrigate> items;
    }
}
