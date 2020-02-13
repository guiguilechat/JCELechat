package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceRogueDroneBattleCruiser
    extends Entity
{
    public static final DeadspaceRogueDroneBattleCruiser.MetaGroup METAGROUP = new DeadspaceRogueDroneBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceRogueDroneBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceRogueDroneBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceRogueDroneBattleCruiser.yaml";
        private Map<String, DeadspaceRogueDroneBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceRogueDroneBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  801;
        }

        @Override
        public String getName() {
            return "DeadspaceRogueDroneBattleCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceRogueDroneBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceRogueDroneBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceRogueDroneBattleCruiser> items;
        }
    }
}
