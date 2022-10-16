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

public class Drugs
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Drugs.MetaGroup METAGROUP = new Drugs.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Drugs> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Drugs>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/Drugs.yaml";
        private Map<String, Drugs> cache = (null);

        @Override
        public IMetaCategory<? super Drugs> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  313;
        }

        @Override
        public String getName() {
            return "Drugs";
        }

        @Override
        public synchronized Map<String, Drugs> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Drugs.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Drugs> types;
        }
    }
}
