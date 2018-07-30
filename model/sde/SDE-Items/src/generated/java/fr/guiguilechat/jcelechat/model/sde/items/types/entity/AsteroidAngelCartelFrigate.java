package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelFrigate.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  550;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelFrigate.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidAngelCartelFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidAngelCartelFrigate> items;
    }
}
