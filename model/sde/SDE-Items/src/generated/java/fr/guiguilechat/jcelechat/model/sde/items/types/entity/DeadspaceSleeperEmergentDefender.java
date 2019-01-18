package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperEmergentDefender
    extends Entity
{
    public static final DeadspaceSleeperEmergentDefender.MetaGroup METAGROUP = new DeadspaceSleeperEmergentDefender.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperEmergentDefender> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperEmergentDefender>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperEmergentDefender.yaml";
        private Map<String, DeadspaceSleeperEmergentDefender> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperEmergentDefender> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  986;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperEmergentDefender";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperEmergentDefender> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperEmergentDefender.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperEmergentDefender> items;
        }
    }
}
