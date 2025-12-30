package fr.guiguilechat.jcelechat.model.sde.types.commodity;

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
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;

public class PeculiarMaterials
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final PeculiarMaterials.MetaGroup METAGROUP = new PeculiarMaterials.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PeculiarMaterials> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PeculiarMaterials>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/PeculiarMaterials.yaml";
        private Map<Integer, PeculiarMaterials> cache = (null);

        @Override
        public IMetaCategory<? super PeculiarMaterials> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4165;
        }

        @Override
        public String getName() {
            return "PeculiarMaterials";
        }

        @Override
        public synchronized Map<Integer, PeculiarMaterials> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PeculiarMaterials.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, PeculiarMaterials> types;
        }
    }
}
