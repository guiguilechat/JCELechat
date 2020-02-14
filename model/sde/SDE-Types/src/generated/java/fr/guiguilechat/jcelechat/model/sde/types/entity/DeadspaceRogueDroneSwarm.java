package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceRogueDroneSwarm
    extends Entity
{
    public static final DeadspaceRogueDroneSwarm.MetaGroup METAGROUP = new DeadspaceRogueDroneSwarm.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceRogueDroneSwarm> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceRogueDroneSwarm>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceRogueDroneSwarm.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceRogueDroneSwarm.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceRogueDroneSwarm> types;
        }
    }
}
