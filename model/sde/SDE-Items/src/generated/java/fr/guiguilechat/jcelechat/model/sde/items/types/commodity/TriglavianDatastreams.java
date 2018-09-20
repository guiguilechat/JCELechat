package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class TriglavianDatastreams
    extends Commodity
{
    public final static TriglavianDatastreams.MetaGroup METAGROUP = new TriglavianDatastreams.MetaGroup();

    @Override
    public IMetaGroup<TriglavianDatastreams> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TriglavianDatastreams>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/TriglavianDatastreams.yaml";
        private Map<String, TriglavianDatastreams> cache = (null);

        @Override
        public IMetaCategory<? super TriglavianDatastreams> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2002;
        }

        @Override
        public String getName() {
            return "TriglavianDatastreams";
        }

        @Override
        public synchronized Map<String, TriglavianDatastreams> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(TriglavianDatastreams.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TriglavianDatastreams> items;
        }
    }
}
