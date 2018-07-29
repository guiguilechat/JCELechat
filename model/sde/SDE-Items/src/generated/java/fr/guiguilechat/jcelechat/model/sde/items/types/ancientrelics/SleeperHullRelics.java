package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;

public class SleeperHullRelics
    extends AncientRelics
{
    public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperHullRelics.yaml";
    private static LinkedHashMap<String, SleeperHullRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  997;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperHullRelics.class;
    }

    public static synchronized LinkedHashMap<String, SleeperHullRelics> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperHullRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperHullRelics> items;
    }
}
