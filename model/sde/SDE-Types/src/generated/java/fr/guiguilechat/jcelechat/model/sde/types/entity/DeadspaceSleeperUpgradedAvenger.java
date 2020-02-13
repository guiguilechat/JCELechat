package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperUpgradedAvenger
    extends Entity
{
    public static final DeadspaceSleeperUpgradedAvenger.MetaGroup METAGROUP = new DeadspaceSleeperUpgradedAvenger.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperUpgradedAvenger> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperUpgradedAvenger>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperUpgradedAvenger.yaml";
        private Map<String, DeadspaceSleeperUpgradedAvenger> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperUpgradedAvenger> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1529;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperUpgradedAvenger";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperUpgradedAvenger> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperUpgradedAvenger.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperUpgradedAvenger> items;
        }
    }
}
