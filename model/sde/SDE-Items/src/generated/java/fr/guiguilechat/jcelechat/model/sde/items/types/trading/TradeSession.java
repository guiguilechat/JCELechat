package fr.guiguilechat.jcelechat.model.sde.items.types.trading;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Trading;
import org.yaml.snakeyaml.Yaml;

public class TradeSession
    extends Trading
{
    public final static TradeSession.MetaGroup METAGROUP = new TradeSession.MetaGroup();

    @Override
    public IMetaGroup<TradeSession> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TradeSession>
    {
        public final static String RESOURCE_PATH = "SDE/items/trading/TradeSession.yaml";
        private Map<String, TradeSession> cache = (null);

        @Override
        public IMetaCategory<? super TradeSession> category() {
            return Trading.METACAT;
        }

        @Override
        public int getGroupId() {
            return  95;
        }

        @Override
        public String getName() {
            return "TradeSession";
        }

        @Override
        public synchronized Map<String, TradeSession> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(TradeSession.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TradeSession> items;
        }
    }
}
