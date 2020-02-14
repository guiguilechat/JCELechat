package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasFrigate
    extends Entity
{
    public static final AsteroidGuristasFrigate.MetaGroup METAGROUP = new AsteroidGuristasFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidGuristasFrigate.yaml";
        private Map<String, AsteroidGuristasFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  562;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidGuristasFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasFrigate> types;
        }
    }
}
