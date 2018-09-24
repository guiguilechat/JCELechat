package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCommanderFrigate
    extends Entity
{
    public final static AsteroidBloodRaidersCommanderFrigate.MetaGroup METAGROUP = new AsteroidBloodRaidersCommanderFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersCommanderFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersCommanderFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersCommanderFrigate.yaml";
        private Map<String, AsteroidBloodRaidersCommanderFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersCommanderFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  792;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersCommanderFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersCommanderFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersCommanderFrigate> items;
        }
    }
}
