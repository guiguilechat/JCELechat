package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisFrigate.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  572;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisFrigate.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSerpentisFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSerpentisFrigate> items;
    }
}
