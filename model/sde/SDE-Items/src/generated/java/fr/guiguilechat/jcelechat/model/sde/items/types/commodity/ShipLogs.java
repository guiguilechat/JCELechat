package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ShipLogs
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/ShipLogs.yaml";
    private static Map<String, ShipLogs> cache = (null);

    @Override
    public int getGroupId() {
        return  369;
    }

    @Override
    public Class<?> getGroup() {
        return ShipLogs.class;
    }

    public static synchronized Map<String, ShipLogs> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ShipLogs.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ShipLogs> items;
    }
}
