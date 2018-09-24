package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperAwakenedSentinel
    extends Entity
{
    public final static DeadspaceSleeperAwakenedSentinel.MetaGroup METAGROUP = new DeadspaceSleeperAwakenedSentinel.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperAwakenedSentinel> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperAwakenedSentinel>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperAwakenedSentinel.yaml";
        private Map<String, DeadspaceSleeperAwakenedSentinel> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSleeperAwakenedSentinel> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  960;
        }

        @Override
        public String getName() {
            return "DeadspaceSleeperAwakenedSentinel";
        }

        @Override
        public synchronized Map<String, DeadspaceSleeperAwakenedSentinel> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperAwakenedSentinel.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperAwakenedSentinel> items;
        }
    }
}
