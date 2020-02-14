package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceGuristasCruiser
    extends Entity
{
    public static final DeadspaceGuristasCruiser.MetaGroup METAGROUP = new DeadspaceGuristasCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceGuristasCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceGuristasCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceGuristasCruiser.yaml";
        private Map<String, DeadspaceGuristasCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceGuristasCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  613;
        }

        @Override
        public String getName() {
            return "DeadspaceGuristasCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceGuristasCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceGuristasCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceGuristasCruiser> types;
        }
    }
}
