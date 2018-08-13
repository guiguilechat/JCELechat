package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ObsoleteBooks
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/ObsoleteBooks.yaml";
    private static Map<String, ObsoleteBooks> cache = (null);

    @Override
    public int getGroupId() {
        return  267;
    }

    @Override
    public Class<?> getGroup() {
        return ObsoleteBooks.class;
    }

    public static synchronized Map<String, ObsoleteBooks> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ObsoleteBooks.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ObsoleteBooks> items;
    }
}
