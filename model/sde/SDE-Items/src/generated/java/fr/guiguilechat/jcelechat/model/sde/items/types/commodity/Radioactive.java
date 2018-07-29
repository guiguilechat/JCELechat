package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class Radioactive
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/Radioactive.yaml";
    private static LinkedHashMap<String, Radioactive> cache = (null);

    @Override
    public int getGroupId() {
        return  282;
    }

    @Override
    public Class<?> getGroup() {
        return Radioactive.class;
    }

    public static synchronized LinkedHashMap<String, Radioactive> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Radioactive.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Radioactive> items;
    }
}
