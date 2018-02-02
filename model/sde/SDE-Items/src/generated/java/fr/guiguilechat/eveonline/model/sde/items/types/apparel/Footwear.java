package fr.guiguilechat.eveonline.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Footwear
    extends Apparel
{
    public final static String RESOURCE_PATH = "SDE/items/apparel/Footwear.yaml";
    private static LinkedHashMap<String, Footwear> cache = (null);

    @Override
    public int getGroupId() {
        return  1091;
    }

    @Override
    public Class<?> getGroup() {
        return Footwear.class;
    }

    public static synchronized LinkedHashMap<String, Footwear> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Footwear.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Footwear> items;
    }
}
