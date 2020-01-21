package fr.guiguilechat.jcelechat.model.sde.items.types.skins;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.SKINs;
import org.yaml.snakeyaml.Yaml;

public class VolatileSKIN
    extends SKINs
{
    public static final VolatileSKIN.MetaGroup METAGROUP = new VolatileSKIN.MetaGroup();

    @Override
    public IMetaGroup<VolatileSKIN> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<VolatileSKIN>
    {
        public static final String RESOURCE_PATH = "SDE/items/skins/VolatileSKIN.yaml";
        private Map<String, VolatileSKIN> cache = (null);

        @Override
        public IMetaCategory<? super VolatileSKIN> category() {
            return SKINs.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1951;
        }

        @Override
        public String getName() {
            return "VolatileSKIN";
        }

        @Override
        public synchronized Map<String, VolatileSKIN> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(VolatileSKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, VolatileSKIN> items;
        }
    }
}
