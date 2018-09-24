package fr.guiguilechat.jcelechat.model.sde.items.types.system;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import org.yaml.snakeyaml.Yaml;

public class System
    extends fr.guiguilechat.jcelechat.model.sde.items.types.System
{
    public final static System.MetaGroup METAGROUP = new System.MetaGroup();

    @Override
    public IMetaGroup<System> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<System>
    {
        public final static String RESOURCE_PATH = "SDE/items/system/System.yaml";
        private Map<String, System> cache = (null);

        @Override
        public IMetaCategory<? super System> category() {
            return fr.guiguilechat.jcelechat.model.sde.items.types.System.METACAT;
        }

        @Override
        public int getGroupId() {
            return  0;
        }

        @Override
        public String getName() {
            return "System";
        }

        @Override
        public synchronized Map<String, System> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(System.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, System> items;
        }
    }
}
