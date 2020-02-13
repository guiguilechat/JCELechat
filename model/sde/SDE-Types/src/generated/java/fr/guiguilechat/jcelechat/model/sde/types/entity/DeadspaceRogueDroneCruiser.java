package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceRogueDroneCruiser
    extends Entity
{
    public static final DeadspaceRogueDroneCruiser.MetaGroup METAGROUP = new DeadspaceRogueDroneCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceRogueDroneCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceRogueDroneCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceRogueDroneCruiser.yaml";
        private Map<String, DeadspaceRogueDroneCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceRogueDroneCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  803;
        }

        @Override
        public String getName() {
            return "DeadspaceRogueDroneCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceRogueDroneCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceRogueDroneCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceRogueDroneCruiser> items;
        }
    }
}
