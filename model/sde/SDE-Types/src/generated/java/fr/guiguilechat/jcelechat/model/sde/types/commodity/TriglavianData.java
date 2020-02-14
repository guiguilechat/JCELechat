package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class TriglavianData
    extends Commodity
{
    public static final TriglavianData.MetaGroup METAGROUP = new TriglavianData.MetaGroup();

    @Override
    public IMetaGroup<TriglavianData> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TriglavianData>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/TriglavianData.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(TriglavianData.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TriglavianData> types;
        }
    }
}
