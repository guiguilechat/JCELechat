package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class SleeperComponents
    extends Commodity
{
    public static final SleeperComponents.MetaGroup METAGROUP = new SleeperComponents.MetaGroup();

    @Override
    public IMetaGroup<SleeperComponents> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SleeperComponents>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/SleeperComponents.yaml";
        private Map<String, SleeperComponents> cache = (null);

        @Override
        public IMetaCategory<? super SleeperComponents> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  880;
        }

        @Override
        public String getName() {
            return "SleeperComponents";
        }

        @Override
        public synchronized Map<String, SleeperComponents> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SleeperComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SleeperComponents> items;
        }
    }
}
