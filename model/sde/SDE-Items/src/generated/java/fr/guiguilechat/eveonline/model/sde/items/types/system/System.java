package fr.guiguilechat.eveonline.model.sde.items.types.system;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;

public class System
    extends fr.guiguilechat.eveonline.model.sde.items.types.System
{
    public final static String RESOURCE_PATH = "SDE/items/system/System.yaml";
    private static LinkedHashMap<String, System> cache = (null);

    @Override
    public int getGroupId() {
        return  0;
    }

    @Override
    public Class<?> getGroup() {
        return System.class;
    }

    public static synchronized LinkedHashMap<String, System> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(System.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, System> items;
    }
}
