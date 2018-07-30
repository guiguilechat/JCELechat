package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class LargeCollidableShip
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/LargeCollidableShip.yaml";
    private static LinkedHashMap<String, LargeCollidableShip> cache = (null);

    @Override
    public int getGroupId() {
        return  784;
    }

    @Override
    public Class<?> getGroup() {
        return LargeCollidableShip.class;
    }

    public static synchronized LinkedHashMap<String, LargeCollidableShip> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(LargeCollidableShip.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, LargeCollidableShip> items;
    }
}
