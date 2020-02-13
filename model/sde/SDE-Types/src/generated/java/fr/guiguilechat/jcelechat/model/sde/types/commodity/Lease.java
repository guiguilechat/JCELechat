package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Lease
    extends Commodity
{
    public static final Lease.MetaGroup METAGROUP = new Lease.MetaGroup();

    @Override
    public IMetaGroup<Lease> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Lease>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/Lease.yaml";
        private Map<String, Lease> cache = (null);

        @Override
        public IMetaCategory<? super Lease> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  652;
        }

        @Override
        public String getName() {
            return "Lease";
        }

        @Override
        public synchronized Map<String, Lease> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Lease.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Lease> items;
        }
    }
}
