package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Refinables
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Refinables.MetaGroup METAGROUP = new Refinables.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Refinables> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Refinables>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/Refinables.yaml";
        private Map<Integer, Refinables> cache = (null);

        @Override
        public IMetaCategory<? super Refinables> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  355;
        }

        @Override
        public String getName() {
            return "Refinables";
        }

        @Override
        public synchronized Map<Integer, Refinables> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Refinables.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Refinables> types;
        }
    }
}
