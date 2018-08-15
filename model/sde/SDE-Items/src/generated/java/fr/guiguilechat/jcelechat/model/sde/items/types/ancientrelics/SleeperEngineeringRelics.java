package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperEngineeringRelics
    extends AncientRelics
{
    public final static SleeperEngineeringRelics.MetaGroup METAGROUP = new SleeperEngineeringRelics.MetaGroup();

    @Override
    public IMetaGroup<SleeperEngineeringRelics> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SleeperEngineeringRelics>
    {
        public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperEngineeringRelics.yaml";
        private Map<String, SleeperEngineeringRelics> cache = (null);

        @Override
        public IMetaCategory<? super SleeperEngineeringRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public int getGroupId() {
            return  992;
        }

        @Override
        public String getName() {
            return "SleeperEngineeringRelics";
        }

        @Override
        public synchronized Map<String, SleeperEngineeringRelics> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SleeperEngineeringRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SleeperEngineeringRelics> items;
        }
    }
}