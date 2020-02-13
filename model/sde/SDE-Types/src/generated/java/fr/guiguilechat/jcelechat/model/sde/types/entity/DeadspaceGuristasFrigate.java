package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceGuristasFrigate
    extends Entity
{
    public static final DeadspaceGuristasFrigate.MetaGroup METAGROUP = new DeadspaceGuristasFrigate.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceGuristasFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceGuristasFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceGuristasFrigate.yaml";
        private Map<String, DeadspaceGuristasFrigate> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceGuristasFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  615;
        }

        @Override
        public String getName() {
            return "DeadspaceGuristasFrigate";
        }

        @Override
        public synchronized Map<String, DeadspaceGuristasFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceGuristasFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceGuristasFrigate> items;
        }
    }
}
