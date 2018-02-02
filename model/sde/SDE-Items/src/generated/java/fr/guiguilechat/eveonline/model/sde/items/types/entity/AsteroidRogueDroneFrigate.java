package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneFrigate.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  759;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneFrigate.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidRogueDroneFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidRogueDroneFrigate> items;
    }
}
