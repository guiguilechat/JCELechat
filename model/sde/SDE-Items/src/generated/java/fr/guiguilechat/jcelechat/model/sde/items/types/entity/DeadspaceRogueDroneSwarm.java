package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceRogueDroneSwarm
    extends Entity
{
    public final static DeadspaceRogueDroneSwarm.MetaGroup METAGROUP = new DeadspaceRogueDroneSwarm.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceRogueDroneSwarm> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceRogueDroneSwarm>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceRogueDroneSwarm.yaml";
        private Map<String, DeadspaceRogueDroneSwarm> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceRogueDroneSwarm> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  806;
        }

        @Override
        public String getName() {
            return "DeadspaceRogueDroneSwarm";
        }

        @Override
        public synchronized Map<String, DeadspaceRogueDroneSwarm> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceRogueDroneSwarm.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceRogueDroneSwarm> items;
        }
    }
}
