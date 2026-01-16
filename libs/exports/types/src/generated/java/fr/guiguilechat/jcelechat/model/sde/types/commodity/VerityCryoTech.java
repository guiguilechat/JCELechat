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

public class VerityCryoTech
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final VerityCryoTech.MetaGroup METAGROUP = new VerityCryoTech.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<VerityCryoTech> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<VerityCryoTech>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/VerityCryoTech.yaml";
        private Map<Integer, VerityCryoTech> cache = (null);

        @Override
        public IMetaCategory<? super VerityCryoTech> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4972;
        }

        @Override
        public String getName() {
            return "VerityCryoTech";
        }

        @Override
        public synchronized Map<Integer, VerityCryoTech> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(VerityCryoTech.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, VerityCryoTech> types;
        }
    }
}
