package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationOfficer
    extends Entity
{
    public static final AsteroidSanshaSNationOfficer.MetaGroup METAGROUP = new AsteroidSanshaSNationOfficer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationOfficer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationOfficer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationOfficer.yaml";
        private Map<String, AsteroidSanshaSNationOfficer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationOfficer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  569;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationOfficer";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationOfficer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationOfficer> items;
        }
    }
}
