package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerSBelongings
    extends Entity
{
    public static final DeadspaceOverseerSBelongings.MetaGroup METAGROUP = new DeadspaceOverseerSBelongings.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceOverseerSBelongings> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceOverseerSBelongings>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceOverseerSBelongings.yaml";
        private Map<String, DeadspaceOverseerSBelongings> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceOverseerSBelongings> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  496;
        }

        @Override
        public String getName() {
            return "DeadspaceOverseerSBelongings";
        }

        @Override
        public synchronized Map<String, DeadspaceOverseerSBelongings> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceOverseerSBelongings.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceOverseerSBelongings> items;
        }
    }
}
