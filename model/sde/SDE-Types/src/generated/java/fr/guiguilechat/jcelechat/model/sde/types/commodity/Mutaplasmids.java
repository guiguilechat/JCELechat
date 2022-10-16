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

public class Mutaplasmids
    extends Commodity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Mutaplasmids.MetaGroup METAGROUP = new Mutaplasmids.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Mutaplasmids> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Mutaplasmids>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/Mutaplasmids.yaml";
        private Map<String, Mutaplasmids> cache = (null);

        @Override
        public IMetaCategory<? super Mutaplasmids> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1964;
        }

        @Override
        public String getName() {
            return "Mutaplasmids";
        }

        @Override
        public synchronized Map<String, Mutaplasmids> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Mutaplasmids.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Mutaplasmids> types;
        }
    }
}
