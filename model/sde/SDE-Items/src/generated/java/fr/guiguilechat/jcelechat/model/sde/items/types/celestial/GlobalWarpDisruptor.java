package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class GlobalWarpDisruptor
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/GlobalWarpDisruptor.yaml";
    private static LinkedHashMap<String, GlobalWarpDisruptor> cache = (null);

    @Override
    public int getGroupId() {
        return  368;
    }

    @Override
    public Class<?> getGroup() {
        return GlobalWarpDisruptor.class;
    }

    public static synchronized LinkedHashMap<String, GlobalWarpDisruptor> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GlobalWarpDisruptor.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, GlobalWarpDisruptor> items;
    }
}
