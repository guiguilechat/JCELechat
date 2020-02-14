package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceGuristasDestroyer
    extends Entity
{
    public static final DeadspaceGuristasDestroyer.MetaGroup METAGROUP = new DeadspaceGuristasDestroyer.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceGuristasDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceGuristasDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceGuristasDestroyer.yaml";
        private Map<String, DeadspaceGuristasDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceGuristasDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  614;
        }

        @Override
        public String getName() {
            return "DeadspaceGuristasDestroyer";
        }

        @Override
        public synchronized Map<String, DeadspaceGuristasDestroyer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceGuristasDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceGuristasDestroyer> types;
        }
    }
}
