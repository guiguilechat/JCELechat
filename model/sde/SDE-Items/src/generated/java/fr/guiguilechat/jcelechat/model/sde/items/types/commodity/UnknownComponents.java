package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class UnknownComponents
    extends Commodity
{
    public static final UnknownComponents.MetaGroup METAGROUP = new UnknownComponents.MetaGroup();

    @Override
    public IMetaGroup<UnknownComponents> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<UnknownComponents>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/UnknownComponents.yaml";
        private Map<String, UnknownComponents> cache = (null);

        @Override
        public IMetaCategory<? super UnknownComponents> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1314;
        }

        @Override
        public String getName() {
            return "UnknownComponents";
        }

        @Override
        public synchronized Map<String, UnknownComponents> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(UnknownComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, UnknownComponents> items;
        }
    }
}
