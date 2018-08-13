package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class Mineral
    extends Material
{
    public final static String RESOURCE_PATH = "SDE/items/material/Mineral.yaml";
    private static Map<String, Mineral> cache = (null);

    @Override
    public int getGroupId() {
        return  18;
    }

    @Override
    public Class<?> getGroup() {
        return Mineral.class;
    }

    public static synchronized Map<String, Mineral> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Mineral.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Mineral> items;
    }
}
