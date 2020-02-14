package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperEmergentSentinel
    extends Entity
{
    public static final DeadspaceSleeperEmergentSentinel.MetaGroup METAGROUP = new DeadspaceSleeperEmergentSentinel.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSleeperEmergentSentinel> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSleeperEmergentSentinel>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceSleeperEmergentSentinel.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceSleeperEmergentSentinel.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSleeperEmergentSentinel> types;
        }
    }
}
