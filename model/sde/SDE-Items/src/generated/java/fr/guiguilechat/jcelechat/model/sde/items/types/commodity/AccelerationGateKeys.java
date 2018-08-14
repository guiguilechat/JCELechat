package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class AccelerationGateKeys
    extends Commodity
{
    public final static AccelerationGateKeys.MetaGroup METAGROUP = new AccelerationGateKeys.MetaGroup();

    @Override
    public IMetaGroup<AccelerationGateKeys> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AccelerationGateKeys>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/AccelerationGateKeys.yaml";
        private Map<String, AccelerationGateKeys> cache = (null);

        @Override
        public IMetaCategory<? super AccelerationGateKeys> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  474;
        }

        @Override
        public String getName() {
            return "AccelerationGateKeys";
        }

        @Override
        public synchronized Map<String, AccelerationGateKeys> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AccelerationGateKeys.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AccelerationGateKeys> items;
        }
    }
}
