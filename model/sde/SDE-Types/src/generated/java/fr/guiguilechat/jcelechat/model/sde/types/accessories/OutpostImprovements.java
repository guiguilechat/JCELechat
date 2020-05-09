package fr.guiguilechat.jcelechat.model.sde.types.accessories;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class OutpostImprovements
    extends Accessories
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final OutpostImprovements.MetaGroup METAGROUP = new OutpostImprovements.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<OutpostImprovements> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<OutpostImprovements>
    {
        public static final String RESOURCE_PATH = "SDE/types/accessories/OutpostImprovements.yaml";
        private Map<String, OutpostImprovements> cache = (null);

        @Override
        public IMetaCategory<? super OutpostImprovements> category() {
            return Accessories.METACAT;
        }

        @Override
        public int getGroupId() {
            return  872;
        }

        @Override
        public String getName() {
            return "OutpostImprovements";
        }

        @Override
        public synchronized Map<String, OutpostImprovements> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(OutpostImprovements.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, OutpostImprovements> types;
        }
    }
}
