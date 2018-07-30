package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Identification
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/Identification.yaml";
    private static LinkedHashMap<String, Identification> cache = (null);

    @Override
    public int getGroupId() {
        return  521;
    }

    @Override
    public Class<?> getGroup() {
        return Identification.class;
    }

    public static synchronized LinkedHashMap<String, Identification> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Identification.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Identification> items;
    }
}
