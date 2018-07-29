package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Planet
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/Planet.yaml";
    private static LinkedHashMap<String, Planet> cache = (null);

    @Override
    public int getGroupId() {
        return  7;
    }

    @Override
    public Class<?> getGroup() {
        return Planet.class;
    }

    public static synchronized LinkedHashMap<String, Planet> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Planet.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Planet> items;
    }
}
