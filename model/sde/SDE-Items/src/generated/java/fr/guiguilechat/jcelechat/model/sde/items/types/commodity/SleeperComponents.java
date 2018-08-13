package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class SleeperComponents
    extends Commodity
{
    public final static SleeperComponents.MetaGroup METAGROUP = new SleeperComponents.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/SleeperComponents.yaml";
    private static Map<String, SleeperComponents> cache = (null);

    @Override
    public int getGroupId() {
        return  880;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperComponents> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SleeperComponents> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperComponents> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperComponents>
    {

        @Override
        public MetaCategory<? super SleeperComponents> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "SleeperComponents";
        }

        @Override
        public Collection<SleeperComponents> items() {
            return (load().values());
        }
    }
}
