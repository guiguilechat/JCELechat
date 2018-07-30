package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneBattleCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  755;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneBattleCruiser.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidRogueDroneBattleCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidRogueDroneBattleCruiser> items;
    }
}
