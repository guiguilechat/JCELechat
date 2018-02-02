package fr.guiguilechat.eveonline.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

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
