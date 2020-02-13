package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperSleeplessDefender
    extends Entity
{
    public static final DeadspaceSleeperSleeplessDefender.MetaGroup METAGROUP = new DeadspaceSleeperSleeplessDefender.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperSleeplessDefender> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperSleeplessDefender>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperSleeplessDefender.yaml";
        private Map<String, DeadspaceSleeperSleeplessDefender> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperSleeplessDefender> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  982;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperSleeplessDefender";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperSleeplessDefender> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperSleeplessDefender.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperSleeplessDefender> items;
        }
    }
}
