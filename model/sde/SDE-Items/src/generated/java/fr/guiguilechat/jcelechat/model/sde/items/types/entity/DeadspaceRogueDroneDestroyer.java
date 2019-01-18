package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceRogueDroneDestroyer
    extends Entity
{
    public static final DeadspaceRogueDroneDestroyer.MetaGroup METAGROUP = new DeadspaceRogueDroneDestroyer.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceRogueDroneDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceRogueDroneDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceRogueDroneDestroyer.yaml";
        private Map<String, DeadspaceRogueDroneDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceRogueDroneDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  804;
        }

        @Override
        public String getName() {
            return "DeadspaceRogueDroneDestroyer";
        }

        @Override
        public synchronized Map<String, DeadspaceRogueDroneDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceRogueDroneDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceRogueDroneDestroyer> items;
        }
    }
}
