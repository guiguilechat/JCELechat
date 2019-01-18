package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersHauler
    extends Entity
{
    public static final AsteroidBloodRaidersHauler.MetaGroup METAGROUP = new AsteroidBloodRaidersHauler.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersHauler> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersHauler>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersHauler.yaml";
        private Map<String, AsteroidBloodRaidersHauler> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersHauler> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  558;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersHauler";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersHauler> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersHauler.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersHauler> items;
        }
    }
}
