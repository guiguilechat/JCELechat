package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Livestock
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/Livestock.yaml";
    private static LinkedHashMap<String, Livestock> cache = (null);

    @Override
    public int getGroupId() {
        return  283;
    }

    @Override
    public Class<?> getGroup() {
        return Livestock.class;
    }

    public static synchronized LinkedHashMap<String, Livestock> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Livestock.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Livestock> items;
    }
}
