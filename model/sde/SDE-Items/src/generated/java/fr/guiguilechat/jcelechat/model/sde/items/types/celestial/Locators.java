package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class Locators
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/Locators.yaml";
    private static LinkedHashMap<String, Locators> cache = (null);

    @Override
    public int getGroupId() {
        return  1973;
    }

    @Override
    public Class<?> getGroup() {
        return Locators.class;
    }

    public static synchronized LinkedHashMap<String, Locators> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Locators.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Locators> items;
    }
}
