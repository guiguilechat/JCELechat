package fr.guiguilechat.jcelechat.model.sde.items.types.trading;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import org.yaml.snakeyaml.Yaml;

public class Trading
    extends fr.guiguilechat.jcelechat.model.sde.items.types.Trading
{
    public static final Trading.MetaGroup METAGROUP = new Trading.MetaGroup();

    @Override
    public IMetaGroup<Trading> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Trading>
    {
        public static final String RESOURCE_PATH = "SDE/items/trading/Trading.yaml";
        private Map<String, Trading> cache = (null);

        @Override
        public IMetaCategory<? super Trading> category() {
            return fr.guiguilechat.jcelechat.model.sde.items.types.Trading.METACAT;
        }

        @Override
        public int getGroupId() {
            return  94;
        }

        @Override
        public String getName() {
            return "Trading";
        }

        @Override
        public synchronized Map<String, Trading> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Trading.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Trading> items;
        }
    }
}
