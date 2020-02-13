package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperEmergentPatroller
    extends Entity
{
    public static final DeadspaceSleeperEmergentPatroller.MetaGroup METAGROUP = new DeadspaceSleeperEmergentPatroller.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperEmergentPatroller> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperEmergentPatroller>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperEmergentPatroller.yaml";
        private Map<String, DeadspaceSleeperEmergentPatroller> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperEmergentPatroller> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  987;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperEmergentPatroller";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperEmergentPatroller> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperEmergentPatroller.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperEmergentPatroller> items;
        }
    }
}
