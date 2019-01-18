package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerFrigate
    extends Entity
{
    public static final DeadspaceOverseerFrigate.MetaGroup METAGROUP = new DeadspaceOverseerFrigate.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceOverseerFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceOverseerFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceOverseerFrigate.yaml";
        private Map<String, DeadspaceOverseerFrigate> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceOverseerFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  819;
        }

        @Override
        public String getName() {
            return "DeadspaceOverseerFrigate";
        }

        @Override
        public synchronized Map<String, DeadspaceOverseerFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceOverseerFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceOverseerFrigate> items;
        }
    }
}
