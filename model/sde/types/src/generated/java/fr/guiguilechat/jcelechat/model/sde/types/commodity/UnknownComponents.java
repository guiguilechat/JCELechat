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

public class UnknownComponents
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final UnknownComponents.MetaGroup METAGROUP = new UnknownComponents.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<UnknownComponents> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<UnknownComponents>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/UnknownComponents.yaml";
        private Map<Integer, UnknownComponents> cache = (null);

        @Override
        public IMetaCategory<? super UnknownComponents> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1314;
        }

        @Override
        public String getName() {
            return "UnknownComponents";
        }

        @Override
        public synchronized Map<Integer, UnknownComponents> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(UnknownComponents.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, UnknownComponents> types;
        }
    }
}
