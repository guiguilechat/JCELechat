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
import org.yaml.snakeyaml.Yaml;

public class EmpireInsigniaDrops
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final EmpireInsigniaDrops.MetaGroup METAGROUP = new EmpireInsigniaDrops.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<EmpireInsigniaDrops> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EmpireInsigniaDrops>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/EmpireInsigniaDrops.yaml";
        private Map<String, EmpireInsigniaDrops> cache = (null);

        @Override
        public IMetaCategory<? super EmpireInsigniaDrops> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  409;
        }

        @Override
        public String getName() {
            return "EmpireInsigniaDrops";
        }

        @Override
        public synchronized Map<String, EmpireInsigniaDrops> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(EmpireInsigniaDrops.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, EmpireInsigniaDrops> types;
        }
    }
}
