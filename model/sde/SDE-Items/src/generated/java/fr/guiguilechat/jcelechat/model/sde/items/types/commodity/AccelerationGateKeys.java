package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class AccelerationGateKeys
    extends Commodity
{
    public final static AccelerationGateKeys.MetaGroup METAGROUP = new AccelerationGateKeys.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/AccelerationGateKeys.yaml";
    private static Map<String, AccelerationGateKeys> cache = (null);

    @Override
    public int getGroupId() {
        return  474;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<AccelerationGateKeys> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, AccelerationGateKeys> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<AccelerationGateKeys>
    {

        @Override
        public MetaCategory<? super AccelerationGateKeys> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "AccelerationGateKeys";
        }

        @Override
        public Collection<AccelerationGateKeys> items() {
            return (load().values());
        }
    }
}
