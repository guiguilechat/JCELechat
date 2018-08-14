package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class TechnicalDataChips
    extends Commodity
{
    public final static TechnicalDataChips.MetaGroup METAGROUP = new TechnicalDataChips.MetaGroup();

    @Override
    public IMetaGroup<TechnicalDataChips> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TechnicalDataChips>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/TechnicalDataChips.yaml";
        private Map<String, TechnicalDataChips> cache = (null);

        @Override
        public IMetaCategory<? super TechnicalDataChips> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1886;
        }

        @Override
        public String getName() {
            return "TechnicalDataChips";
        }

        @Override
        public synchronized Map<String, TechnicalDataChips> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(TechnicalDataChips.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TechnicalDataChips> items;
        }
    }
}
