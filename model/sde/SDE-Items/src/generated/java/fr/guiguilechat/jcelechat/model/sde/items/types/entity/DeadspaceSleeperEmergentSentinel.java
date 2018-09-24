package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperEmergentSentinel
    extends Entity
{
    public final static DeadspaceSleeperEmergentSentinel.MetaGroup METAGROUP = new DeadspaceSleeperEmergentSentinel.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperEmergentSentinel> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperEmergentSentinel>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperEmergentSentinel.yaml";
        private Map<String, DeadspaceSleeperEmergentSentinel> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperEmergentSentinel> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  961;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperEmergentSentinel";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperEmergentSentinel> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperEmergentSentinel.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperEmergentSentinel> items;
        }
    }
}
