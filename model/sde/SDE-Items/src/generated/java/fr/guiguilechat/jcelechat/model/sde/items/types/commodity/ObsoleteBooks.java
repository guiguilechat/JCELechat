package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class ObsoleteBooks
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/ObsoleteBooks.yaml";
    private static LinkedHashMap<String, ObsoleteBooks> cache = (null);

    @Override
    public int getGroupId() {
        return  267;
    }

    @Override
    public Class<?> getGroup() {
        return ObsoleteBooks.class;
    }

    public static synchronized LinkedHashMap<String, ObsoleteBooks> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ObsoleteBooks.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ObsoleteBooks> items;
    }
}
