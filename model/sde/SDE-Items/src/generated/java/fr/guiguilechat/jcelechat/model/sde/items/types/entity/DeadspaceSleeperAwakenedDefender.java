package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperAwakenedDefender
    extends Entity
{
    public static final DeadspaceSleeperAwakenedDefender.MetaGroup METAGROUP = new DeadspaceSleeperAwakenedDefender.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperAwakenedDefender> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperAwakenedDefender>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperAwakenedDefender.yaml";
        private Map<String, DeadspaceSleeperAwakenedDefender> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperAwakenedDefender> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  984;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperAwakenedDefender";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperAwakenedDefender> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperAwakenedDefender.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperAwakenedDefender> items;
        }
    }
}
