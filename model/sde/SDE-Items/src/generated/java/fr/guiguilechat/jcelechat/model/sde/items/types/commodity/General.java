package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class General
    extends Commodity
{
    public final static General.MetaGroup METAGROUP = new General.MetaGroup();

    @Override
    public IMetaGroup<General> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<General>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/General.yaml";
        private Map<String, General> cache = (null);

        @Override
        public IMetaCategory<? super General> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  280;
        }

        @Override
        public String getName() {
            return "General";
        }

        @Override
        public synchronized Map<String, General> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(General.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, General> items;
        }
    }
}
