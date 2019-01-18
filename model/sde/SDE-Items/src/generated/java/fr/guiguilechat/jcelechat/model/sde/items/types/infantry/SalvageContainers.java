package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class SalvageContainers
    extends Infantry
{
    public static final SalvageContainers.MetaGroup METAGROUP = new SalvageContainers.MetaGroup();

    @Override
    public IMetaGroup<SalvageContainers> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SalvageContainers>
    {
        public static final String RESOURCE_PATH = "SDE/items/infantry/SalvageContainers.yaml";
        private Map<String, SalvageContainers> cache = (null);

        @Override
        public IMetaCategory<? super SalvageContainers> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  367774;
        }

        @Override
        public String getName() {
            return "SalvageContainers";
        }

        @Override
        public synchronized Map<String, SalvageContainers> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SalvageContainers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SalvageContainers> items;
        }
    }
}
