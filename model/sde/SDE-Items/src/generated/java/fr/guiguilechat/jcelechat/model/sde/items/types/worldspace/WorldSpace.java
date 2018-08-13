package fr.guiguilechat.jcelechat.model.sde.items.types.worldspace;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class WorldSpace
    extends fr.guiguilechat.jcelechat.model.sde.items.types.WorldSpace
{
    public final static String RESOURCE_PATH = "SDE/items/worldspace/WorldSpace.yaml";
    private static Map<String, WorldSpace> cache = (null);

    @Override
    public int getGroupId() {
        return  935;
    }

    @Override
    public Class<?> getGroup() {
        return WorldSpace.class;
    }

    public static synchronized Map<String, WorldSpace> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(WorldSpace.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, WorldSpace> items;
    }
}
