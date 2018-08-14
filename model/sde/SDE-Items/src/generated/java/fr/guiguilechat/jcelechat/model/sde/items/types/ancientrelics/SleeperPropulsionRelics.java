package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperPropulsionRelics
    extends AncientRelics
{
    public final static SleeperPropulsionRelics.MetaGroup METAGROUP = new SleeperPropulsionRelics.MetaGroup();

    @Override
    public IMetaGroup<SleeperPropulsionRelics> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SleeperPropulsionRelics>
    {
        public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperPropulsionRelics.yaml";
        private Map<String, SleeperPropulsionRelics> cache = (null);

        @Override
        public IMetaCategory<? super SleeperPropulsionRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public int getGroupId() {
            return  971;
        }

        @Override
        public String getName() {
            return "SleeperPropulsionRelics";
        }

        @Override
        public synchronized Map<String, SleeperPropulsionRelics> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SleeperPropulsionRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SleeperPropulsionRelics> items;
        }
    }
}
