package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasFrigate.yaml";
    private static LinkedHashMap<String, AsteroidGuristasFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  562;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasFrigate.class;
    }

    public static LinkedHashMap<String, AsteroidGuristasFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidGuristasFrigate> items;
    }
}
