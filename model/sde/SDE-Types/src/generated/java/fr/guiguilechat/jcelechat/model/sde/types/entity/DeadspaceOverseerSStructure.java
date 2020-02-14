package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerSStructure
    extends Entity
{
    public static final DeadspaceOverseerSStructure.MetaGroup METAGROUP = new DeadspaceOverseerSStructure.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceOverseerSStructure> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceOverseerSStructure>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceOverseerSStructure.yaml";
        private Map<String, DeadspaceOverseerSStructure> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceOverseerSStructure> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  494;
        }

        @Override
        public String getName() {
            return "DeadspaceOverseerSStructure";
        }

        @Override
        public synchronized Map<String, DeadspaceOverseerSStructure> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceOverseerSStructure.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceOverseerSStructure> types;
        }
    }
}
