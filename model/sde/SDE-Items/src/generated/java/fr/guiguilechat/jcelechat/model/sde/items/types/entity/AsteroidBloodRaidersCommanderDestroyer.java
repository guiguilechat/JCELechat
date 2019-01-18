package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCommanderDestroyer
    extends Entity
{
    public static final AsteroidBloodRaidersCommanderDestroyer.MetaGroup METAGROUP = new AsteroidBloodRaidersCommanderDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersCommanderDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersCommanderDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersCommanderDestroyer.yaml";
        private Map<String, AsteroidBloodRaidersCommanderDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersCommanderDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  796;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersCommanderDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersCommanderDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersCommanderDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersCommanderDestroyer> items;
        }
    }
}
