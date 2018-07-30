package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCruiser.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  757;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneCruiser.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidRogueDroneCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidRogueDroneCruiser> items;
    }
}
