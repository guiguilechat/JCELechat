package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class AccelerationGateKeys
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/AccelerationGateKeys.yaml";
    private static LinkedHashMap<String, AccelerationGateKeys> cache = (null);

    @Override
    public int getGroupId() {
        return  474;
    }

    @Override
    public Class<?> getGroup() {
        return AccelerationGateKeys.class;
    }

    public static synchronized LinkedHashMap<String, AccelerationGateKeys> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AccelerationGateKeys.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AccelerationGateKeys> items;
    }
}
