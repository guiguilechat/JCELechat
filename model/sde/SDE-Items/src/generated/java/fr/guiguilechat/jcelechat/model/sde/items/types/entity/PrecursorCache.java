package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class PrecursorCache
    extends Entity
{
    public static final PrecursorCache.MetaGroup METAGROUP = new PrecursorCache.MetaGroup();

    @Override
    public IMetaGroup<PrecursorCache> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PrecursorCache>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/PrecursorCache.yaml";
        private Map<String, PrecursorCache> cache = (null);

        @Override
        public IMetaCategory<? super PrecursorCache> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2009;
        }

        @Override
        public String getName() {
            return "PrecursorCache";
        }

        @Override
        public synchronized Map<String, PrecursorCache> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PrecursorCache.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PrecursorCache> items;
        }
    }
}
