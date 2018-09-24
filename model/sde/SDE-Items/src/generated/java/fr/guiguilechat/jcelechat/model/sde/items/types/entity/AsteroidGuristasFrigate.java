package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasFrigate
    extends Entity
{
    public final static AsteroidGuristasFrigate.MetaGroup METAGROUP = new AsteroidGuristasFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasFrigate.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasFrigate> items;
        }
    }
}
