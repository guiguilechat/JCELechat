package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationDreadnought
    extends Entity
{
    public static final AsteroidSanshaSNationDreadnought.MetaGroup METAGROUP = new AsteroidSanshaSNationDreadnought.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationDreadnought> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationDreadnought>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSanshaSNationDreadnought.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSanshaSNationDreadnought.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationDreadnought> types;
        }
    }
}
