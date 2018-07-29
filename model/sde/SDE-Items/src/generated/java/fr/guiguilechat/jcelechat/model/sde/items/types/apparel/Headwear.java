package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;

public class Headwear
    extends Apparel
{
    public final static String RESOURCE_PATH = "SDE/items/apparel/Headwear.yaml";
    private static LinkedHashMap<String, Headwear> cache = (null);

    @Override
    public int getGroupId() {
        return  1092;
    }

    @Override
    public Class<?> getGroup() {
        return Headwear.class;
    }

    public static synchronized LinkedHashMap<String, Headwear> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Headwear.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Headwear> items;
    }
}
