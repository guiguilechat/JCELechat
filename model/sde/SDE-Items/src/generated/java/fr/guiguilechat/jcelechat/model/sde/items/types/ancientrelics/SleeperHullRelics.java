package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperHullRelics
    extends AncientRelics
{
    public static final SleeperHullRelics.MetaGroup METAGROUP = new SleeperHullRelics.MetaGroup();

    @Override
    public IMetaGroup<SleeperHullRelics> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SleeperHullRelics>
    {
        public static final String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperHullRelics.yaml";
        private Map<String, SleeperHullRelics> cache = (null);

        @Override
        public IMetaCategory<? super SleeperHullRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public int getGroupId() {
            return  997;
        }

        @Override
        public String getName() {
            return "SleeperHullRelics";
        }

        @Override
        public synchronized Map<String, SleeperHullRelics> load() {
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
}
