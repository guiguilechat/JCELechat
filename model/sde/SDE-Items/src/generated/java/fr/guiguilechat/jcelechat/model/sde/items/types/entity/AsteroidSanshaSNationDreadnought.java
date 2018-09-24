package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationDreadnought
    extends Entity
{
    public final static AsteroidSanshaSNationDreadnought.MetaGroup METAGROUP = new AsteroidSanshaSNationDreadnought.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationDreadnought> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationDreadnought>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationDreadnought.yaml";
        private Map<String, AsteroidSanshaSNationDreadnought> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationDreadnought> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1687;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationDreadnought";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationDreadnought> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationDreadnought.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationDreadnought> items;
        }
    }
}
