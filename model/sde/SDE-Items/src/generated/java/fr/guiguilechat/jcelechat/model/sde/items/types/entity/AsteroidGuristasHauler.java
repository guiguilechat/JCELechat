package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasHauler
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasHauler.yaml";
    private static LinkedHashMap<String, AsteroidGuristasHauler> cache = (null);

    @Override
    public int getGroupId() {
        return  563;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasHauler.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidGuristasHauler> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasHauler.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidGuristasHauler> items;
    }
}
