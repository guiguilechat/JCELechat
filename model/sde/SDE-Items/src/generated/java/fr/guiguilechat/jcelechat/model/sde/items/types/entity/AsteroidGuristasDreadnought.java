package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasDreadnought
    extends Entity
{
    public static final AsteroidGuristasDreadnought.MetaGroup METAGROUP = new AsteroidGuristasDreadnought.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasDreadnought> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasDreadnought>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasDreadnought.yaml";
        private Map<String, AsteroidGuristasDreadnought> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasDreadnought> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1685;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasDreadnought";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasDreadnought> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasDreadnought.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasDreadnought> items;
        }
    }
}
