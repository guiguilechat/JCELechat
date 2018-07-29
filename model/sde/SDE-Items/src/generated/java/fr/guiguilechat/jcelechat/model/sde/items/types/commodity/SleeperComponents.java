package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class SleeperComponents
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/SleeperComponents.yaml";
    private static LinkedHashMap<String, SleeperComponents> cache = (null);

    @Override
    public int getGroupId() {
        return  880;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperComponents.class;
    }

    public static synchronized LinkedHashMap<String, SleeperComponents> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperComponents> items;
    }
}
