package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperHullRelics
    extends AncientRelics
{
    public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperHullRelics.yaml";
    private static Map<String, SleeperHullRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  997;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperHullRelics.class;
    }

    public static synchronized Map<String, SleeperHullRelics> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperHullRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperHullRelics> items;
    }
}
