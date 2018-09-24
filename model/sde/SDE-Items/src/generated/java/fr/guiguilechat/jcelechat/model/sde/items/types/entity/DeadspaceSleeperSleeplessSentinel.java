package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperSleeplessSentinel
    extends Entity
{
    public final static DeadspaceSleeperSleeplessSentinel.MetaGroup METAGROUP = new DeadspaceSleeperSleeplessSentinel.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperSleeplessSentinel> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperSleeplessSentinel>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperSleeplessSentinel.yaml";
        private Map<String, DeadspaceSleeperSleeplessSentinel> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperSleeplessSentinel> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  959;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperSleeplessSentinel";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperSleeplessSentinel> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperSleeplessSentinel.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperSleeplessSentinel> items;
        }
    }
}
