package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderCruiser
    extends Entity
{
    public static final AsteroidSanshaSNationCommanderCruiser.MetaGroup METAGROUP = new AsteroidSanshaSNationCommanderCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationCommanderCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationCommanderCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationCommanderCruiser.yaml";
        private Map<String, AsteroidSanshaSNationCommanderCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationCommanderCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  808;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationCommanderCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationCommanderCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationCommanderCruiser> items;
        }
    }
}
