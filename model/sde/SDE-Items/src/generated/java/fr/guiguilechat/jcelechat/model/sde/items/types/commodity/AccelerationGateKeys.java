package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class AccelerationGateKeys
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/AccelerationGateKeys.yaml";
    private static Map<String, AccelerationGateKeys> cache = (null);

    @Override
    public int getGroupId() {
        return  474;
    }

    @Override
    public Class<?> getGroup() {
        return AccelerationGateKeys.class;
    }

    public static synchronized Map<String, AccelerationGateKeys> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AccelerationGateKeys.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, AccelerationGateKeys> items;
    }
}
