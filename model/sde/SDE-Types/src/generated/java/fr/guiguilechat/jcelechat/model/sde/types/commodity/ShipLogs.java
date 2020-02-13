package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ShipLogs
    extends Commodity
{
    public static final ShipLogs.MetaGroup METAGROUP = new ShipLogs.MetaGroup();

    @Override
    public IMetaGroup<ShipLogs> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ShipLogs>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/ShipLogs.yaml";
        private Map<String, ShipLogs> cache = (null);

        @Override
        public IMetaCategory<? super ShipLogs> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  369;
        }

        @Override
        public String getName() {
            return "ShipLogs";
        }

        @Override
        public synchronized Map<String, ShipLogs> load() {
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
    }
}
