package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;

public class Eyewear
    extends Apparel
{
    public final static String RESOURCE_PATH = "SDE/items/apparel/Eyewear.yaml";
    private static LinkedHashMap<String, Eyewear> cache = (null);

    @Override
    public int getGroupId() {
        return  1083;
    }

    @Override
    public Class<?> getGroup() {
        return Eyewear.class;
    }

    public static synchronized LinkedHashMap<String, Eyewear> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Eyewear.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Eyewear> items;
    }
}
