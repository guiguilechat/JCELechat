package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceAngelCartelCruiser
    extends Entity
{
    public static final DeadspaceAngelCartelCruiser.MetaGroup METAGROUP = new DeadspaceAngelCartelCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceAngelCartelCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceAngelCartelCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceAngelCartelCruiser.yaml";
        private Map<String, DeadspaceAngelCartelCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceAngelCartelCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  595;
        }

        @Override
        public String getName() {
            return "DeadspaceAngelCartelCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceAngelCartelCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceAngelCartelCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceAngelCartelCruiser> types;
        }
    }
}
