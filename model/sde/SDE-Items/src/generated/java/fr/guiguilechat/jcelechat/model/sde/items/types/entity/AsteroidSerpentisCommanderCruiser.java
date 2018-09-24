package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderCruiser
    extends Entity
{
    public final static AsteroidSerpentisCommanderCruiser.MetaGroup METAGROUP = new AsteroidSerpentisCommanderCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisCommanderCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisCommanderCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCommanderCruiser.yaml";
        private Map<String, AsteroidSerpentisCommanderCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisCommanderCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  812;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisCommanderCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisCommanderCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisCommanderCruiser> items;
        }
    }
}
