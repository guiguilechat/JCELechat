package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Frozen
    extends Commodity
{
    public static final Frozen.MetaGroup METAGROUP = new Frozen.MetaGroup();

    @Override
    public IMetaGroup<Frozen> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Frozen>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/Frozen.yaml";
        private Map<String, Frozen> cache = (null);

        @Override
        public IMetaCategory<? super Frozen> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  281;
        }

        @Override
        public String getName() {
            return "Frozen";
        }

        @Override
        public synchronized Map<String, Frozen> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Frozen.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Frozen> items;
        }
    }
}
