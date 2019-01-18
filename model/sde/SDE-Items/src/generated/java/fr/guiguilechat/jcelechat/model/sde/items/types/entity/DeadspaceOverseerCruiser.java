package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerCruiser
    extends Entity
{
    public static final DeadspaceOverseerCruiser.MetaGroup METAGROUP = new DeadspaceOverseerCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceOverseerCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceOverseerCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceOverseerCruiser.yaml";
        private Map<String, DeadspaceOverseerCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceOverseerCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  820;
        }

        @Override
        public String getName() {
            return "DeadspaceOverseerCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceOverseerCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceOverseerCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceOverseerCruiser> items;
        }
    }
}
