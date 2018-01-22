package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelCommanderFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelCommanderFrigate.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelCommanderFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  789;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelCommanderFrigate.class;
    }

    public static LinkedHashMap<String, AsteroidAngelCartelCommanderFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidAngelCartelCommanderFrigate> items;
    }
}
