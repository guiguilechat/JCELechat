package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class TriglavianData
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/TriglavianData.yaml";
    private static LinkedHashMap<String, TriglavianData> cache = (null);

    @Override
    public int getGroupId() {
        return  1995;
    }

    @Override
    public Class<?> getGroup() {
        return TriglavianData.class;
    }

    public static synchronized LinkedHashMap<String, TriglavianData> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TriglavianData.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, TriglavianData> items;
    }
}
