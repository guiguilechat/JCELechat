package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class Services
    extends Infantry
{
    public final static Services.MetaGroup METAGROUP = new Services.MetaGroup();

    @Override
    public IMetaGroup<Services> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Services>
    {
        public final static String RESOURCE_PATH = "SDE/items/infantry/Services.yaml";
        private Map<String, Services> cache = (null);

        @Override
        public IMetaCategory<? super Services> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  367487;
        }

        @Override
        public String getName() {
            return "Services";
        }

        @Override
        public synchronized Map<String, Services> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Services.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Services> items;
        }
    }
}
