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

public class InfomorphSystems
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final InfomorphSystems.MetaGroup METAGROUP = new InfomorphSystems.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<InfomorphSystems> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfomorphSystems>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/InfomorphSystems.yaml";
        private Map<Integer, InfomorphSystems> cache = (null);

        @Override
        public IMetaCategory<? super InfomorphSystems> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4824;
        }

        @Override
        public String getName() {
            return "InfomorphSystems";
        }

        @Override
        public synchronized Map<Integer, InfomorphSystems> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(InfomorphSystems.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, InfomorphSystems> types;
        }
    }
}
