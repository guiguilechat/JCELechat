package fr.guiguilechat.eveonline.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Frozen
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/Frozen.yaml";
    private static LinkedHashMap<String, Frozen> cache = (null);

    @Override
    public int getGroupId() {
        return  281;
    }

    @Override
    public Class<?> getGroup() {
        return Frozen.class;
    }

    public static synchronized LinkedHashMap<String, Frozen> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Frozen.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Frozen> items;
    }
}
