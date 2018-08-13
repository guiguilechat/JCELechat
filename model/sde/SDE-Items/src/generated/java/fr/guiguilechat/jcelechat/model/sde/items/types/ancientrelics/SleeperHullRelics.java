package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperHullRelics
    extends AncientRelics
{
    public final static SleeperHullRelics.MetaGroup METAGROUP = new SleeperHullRelics.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperHullRelics.yaml";
    private static Map<String, SleeperHullRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  997;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperHullRelics> getGroup() {
        return METAGROUP;
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperHullRelics>
    {

        @Override
        public MetaCategory<? super SleeperHullRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public String getName() {
            return "SleeperHullRelics";
        }

        @Override
        public Collection<SleeperHullRelics> items() {
            return (load().values());
        }
    }
}
