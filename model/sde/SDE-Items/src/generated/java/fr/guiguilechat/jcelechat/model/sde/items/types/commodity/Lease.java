package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Lease
    extends Commodity
{
    public final static Lease.MetaGroup METAGROUP = new Lease.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/Lease.yaml";
    private static Map<String, Lease> cache = (null);

    @Override
    public int getGroupId() {
        return  652;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Lease> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Lease> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Lease>
    {

        @Override
        public MetaCategory<? super Lease> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "Lease";
        }

        @Override
        public Collection<Lease> items() {
            return (load().values());
        }
    }
}
