package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DestructibleAgentsInSpace
    extends Entity
{
    public final static DestructibleAgentsInSpace.MetaGroup METAGROUP = new DestructibleAgentsInSpace.MetaGroup();

    @Override
    public IMetaGroup<DestructibleAgentsInSpace> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DestructibleAgentsInSpace>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DestructibleAgentsInSpace.yaml";
        private Map<String, DestructibleAgentsInSpace> cache = (null);

        @Override
        public IMetaCategory<? super DestructibleAgentsInSpace> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  715;
        }

        @Override
        public String getName() {
            return "DestructibleAgentsInSpace";
        }

        @Override
        public synchronized Map<String, DestructibleAgentsInSpace> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DestructibleAgentsInSpace.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DestructibleAgentsInSpace> items;
        }
    }
}
