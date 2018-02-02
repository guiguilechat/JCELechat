package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SerpentisEventFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/SerpentisEventFrigate.yaml";
    private static LinkedHashMap<String, SerpentisEventFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1566;
    }

    @Override
    public Class<?> getGroup() {
        return SerpentisEventFrigate.class;
    }

    public static synchronized LinkedHashMap<String, SerpentisEventFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SerpentisEventFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SerpentisEventFrigate> items;
    }
}
