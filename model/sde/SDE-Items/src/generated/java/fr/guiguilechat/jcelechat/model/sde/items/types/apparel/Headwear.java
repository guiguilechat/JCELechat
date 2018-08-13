package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Headwear
    extends Apparel
{
    public final static String RESOURCE_PATH = "SDE/items/apparel/Headwear.yaml";
    private static Map<String, Headwear> cache = (null);

    @Override
    public int getGroupId() {
        return  1092;
    }

    @Override
    public Class<?> getGroup() {
        return Headwear.class;
    }

    public static synchronized Map<String, Headwear> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Headwear.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Headwear> items;
    }
}
