package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Mutaplasmids
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/Mutaplasmids.yaml";
    private static Map<String, Mutaplasmids> cache = (null);

    @Override
    public int getGroupId() {
        return  1964;
    }

    @Override
    public Class<?> getGroup() {
        return Mutaplasmids.class;
    }

    public static synchronized Map<String, Mutaplasmids> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Mutaplasmids.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Mutaplasmids> items;
    }
}
