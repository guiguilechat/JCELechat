package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperAwakenedPatroller
    extends Entity
{
    public static final DeadspaceSleeperAwakenedPatroller.MetaGroup METAGROUP = new DeadspaceSleeperAwakenedPatroller.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperAwakenedPatroller> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperAwakenedPatroller>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperAwakenedPatroller.yaml";
        private Map<String, DeadspaceSleeperAwakenedPatroller> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperAwakenedPatroller> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  985;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperAwakenedPatroller";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperAwakenedPatroller> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperAwakenedPatroller.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperAwakenedPatroller> items;
        }
    }
}
