package fr.guiguilechat.jcelechat.model.sde.types.trading;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Trading;

public class TradeSession
    extends Trading
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final TradeSession.MetaGroup METAGROUP = new TradeSession.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<TradeSession> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TradeSession>
    {
        public static final String RESOURCE_PATH = "SDE/types/trading/TradeSession.yaml";
        private Map<Integer, TradeSession> cache = (null);

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
        public synchronized Map<Integer, TradeSession> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(TradeSession.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, TradeSession> types;
        }
    }
}
