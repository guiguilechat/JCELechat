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

public class Trading
    extends fr.guiguilechat.jcelechat.model.sde.types.Trading
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Trading.MetaGroup METAGROUP = new Trading.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Trading> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Trading>
    {
        public static final String RESOURCE_PATH = "SDE/types/trading/Trading.yaml";
        private Map<Integer, Trading> cache = (null);

        @Override
        public IMetaCategory<? super Trading> category() {
            return fr.guiguilechat.jcelechat.model.sde.types.Trading.METACAT;
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
        public synchronized Map<Integer, Trading> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Trading.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Trading> types;
        }
    }
}
