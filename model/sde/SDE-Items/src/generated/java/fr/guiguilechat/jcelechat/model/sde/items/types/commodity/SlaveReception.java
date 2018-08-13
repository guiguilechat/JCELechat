package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class SlaveReception
    extends Commodity
{
    public final static SlaveReception.MetaGroup METAGROUP = new SlaveReception.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/SlaveReception.yaml";
    private static Map<String, SlaveReception> cache = (null);

    @Override
    public int getGroupId() {
        return  879;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SlaveReception> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SlaveReception> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SlaveReception.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SlaveReception> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SlaveReception>
    {

        @Override
        public MetaCategory<? super SlaveReception> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "SlaveReception";
        }

        @Override
        public Collection<SlaveReception> items() {
            return (load().values());
        }
    }
}
