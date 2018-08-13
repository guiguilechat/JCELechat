package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ShipLogs
    extends Commodity
{
    public final static ShipLogs.MetaGroup METAGROUP = new ShipLogs.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/ShipLogs.yaml";
    private static Map<String, ShipLogs> cache = (null);

    @Override
    public int getGroupId() {
        return  369;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ShipLogs> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, ShipLogs> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ShipLogs.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ShipLogs> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ShipLogs>
    {

        @Override
        public MetaCategory<? super ShipLogs> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "ShipLogs";
        }

        @Override
        public Collection<ShipLogs> items() {
            return (load().values());
        }
    }
}
