package fr.guiguilechat.jcelechat.model.sde.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperDefensiveRelics
    extends AncientRelics
{
    public static final SleeperDefensiveRelics.MetaGroup METAGROUP = new SleeperDefensiveRelics.MetaGroup();

    @Override
    public IMetaGroup<SleeperDefensiveRelics> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SleeperDefensiveRelics>
    {
        public static final String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperDefensiveRelics.yaml";
        private Map<String, SleeperDefensiveRelics> cache = (null);

        @Override
        public IMetaCategory<? super SleeperDefensiveRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public int getGroupId() {
            return  993;
        }

        @Override
        public String getName() {
            return "SleeperDefensiveRelics";
        }

        @Override
        public synchronized Map<String, SleeperDefensiveRelics> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SleeperDefensiveRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SleeperDefensiveRelics> items;
        }
    }
}
