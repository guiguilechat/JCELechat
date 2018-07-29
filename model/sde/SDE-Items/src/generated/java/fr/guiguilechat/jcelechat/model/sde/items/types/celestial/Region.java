package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Region
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/Region.yaml";
    private static LinkedHashMap<String, Region> cache = (null);

    @Override
    public int getGroupId() {
        return  3;
    }

    @Override
    public Class<?> getGroup() {
        return Region.class;
    }

    public static synchronized LinkedHashMap<String, Region> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Region.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Region> items;
    }
}
