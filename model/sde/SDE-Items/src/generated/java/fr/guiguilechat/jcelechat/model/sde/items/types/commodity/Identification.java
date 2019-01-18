package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Identification
    extends Commodity
{
    public static final Identification.MetaGroup METAGROUP = new Identification.MetaGroup();

    @Override
    public IMetaGroup<Identification> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Identification>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/Identification.yaml";
        private Map<String, Identification> cache = (null);

        @Override
        public IMetaCategory<? super Identification> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  521;
        }

        @Override
        public String getName() {
            return "Identification";
        }

        @Override
        public synchronized Map<String, Identification> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Identification.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Identification> items;
        }
    }
}
