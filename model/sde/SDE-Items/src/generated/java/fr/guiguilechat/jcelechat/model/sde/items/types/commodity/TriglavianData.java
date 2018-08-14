package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class TriglavianData
    extends Commodity
{
    public final static TriglavianData.MetaGroup METAGROUP = new TriglavianData.MetaGroup();

    @Override
    public IMetaGroup<TriglavianData> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TriglavianData>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/TriglavianData.yaml";
        private Map<String, TriglavianData> cache = (null);

        @Override
        public IMetaCategory<? super TriglavianData> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1995;
        }

        @Override
        public String getName() {
            return "TriglavianData";
        }

        @Override
        public synchronized Map<String, TriglavianData> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(TriglavianData.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TriglavianData> items;
        }
    }
}
