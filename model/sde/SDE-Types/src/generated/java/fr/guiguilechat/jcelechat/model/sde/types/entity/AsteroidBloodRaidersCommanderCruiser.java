package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCommanderCruiser
    extends Entity
{
    public static final AsteroidBloodRaidersCommanderCruiser.MetaGroup METAGROUP = new AsteroidBloodRaidersCommanderCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersCommanderCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersCommanderCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersCommanderCruiser.yaml";
        private Map<String, AsteroidBloodRaidersCommanderCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersCommanderCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  791;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersCommanderCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersCommanderCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersCommanderCruiser> items;
        }
    }
}
