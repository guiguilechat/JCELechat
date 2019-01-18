package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class SlaveReception
    extends Commodity
{
    public static final SlaveReception.MetaGroup METAGROUP = new SlaveReception.MetaGroup();

    @Override
    public IMetaGroup<SlaveReception> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SlaveReception>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/SlaveReception.yaml";
        private Map<String, SlaveReception> cache = (null);

        @Override
        public IMetaCategory<? super SlaveReception> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  879;
        }

        @Override
        public String getName() {
            return "SlaveReception";
        }

        @Override
        public synchronized Map<String, SlaveReception> load() {
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
    }
}
