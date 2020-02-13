package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperSleeplessPatroller
    extends Entity
{
    public static final DeadspaceSleeperSleeplessPatroller.MetaGroup METAGROUP = new DeadspaceSleeperSleeplessPatroller.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperSleeplessPatroller> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperSleeplessPatroller>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperSleeplessPatroller.yaml";
        private Map<String, DeadspaceSleeperSleeplessPatroller> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperSleeplessPatroller> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  983;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperSleeplessPatroller";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperSleeplessPatroller> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperSleeplessPatroller.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperSleeplessPatroller> items;
        }
    }
}
