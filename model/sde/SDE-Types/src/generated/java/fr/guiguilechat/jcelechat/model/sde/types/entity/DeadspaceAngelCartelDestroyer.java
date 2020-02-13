package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceAngelCartelDestroyer
    extends Entity
{
    public static final DeadspaceAngelCartelDestroyer.MetaGroup METAGROUP = new DeadspaceAngelCartelDestroyer.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceAngelCartelDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceAngelCartelDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceAngelCartelDestroyer.yaml";
        private Map<String, DeadspaceAngelCartelDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceAngelCartelDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  596;
        }

        @Override
        public String getName() {
            return "DeadspaceAngelCartelDestroyer";
        }

        @Override
        public synchronized Map<String, DeadspaceAngelCartelDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceAngelCartelDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceAngelCartelDestroyer> items;
        }
    }
}
