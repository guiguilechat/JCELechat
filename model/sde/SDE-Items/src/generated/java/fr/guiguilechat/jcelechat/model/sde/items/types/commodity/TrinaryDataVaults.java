package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class TrinaryDataVaults
    extends Commodity
{
    public final static TrinaryDataVaults.MetaGroup METAGROUP = new TrinaryDataVaults.MetaGroup();

    @Override
    public IMetaGroup<TrinaryDataVaults> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TrinaryDataVaults>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/TrinaryDataVaults.yaml";
        private Map<String, TrinaryDataVaults> cache = (null);

        @Override
        public IMetaCategory<? super TrinaryDataVaults> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1977;
        }

        @Override
        public String getName() {
            return "TrinaryDataVaults";
        }

        @Override
        public synchronized Map<String, TrinaryDataVaults> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(TrinaryDataVaults.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TrinaryDataVaults> items;
        }
    }
}
