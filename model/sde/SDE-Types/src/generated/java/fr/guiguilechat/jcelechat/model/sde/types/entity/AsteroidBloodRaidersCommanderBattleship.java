package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCommanderBattleship
    extends Entity
{
    public static final AsteroidBloodRaidersCommanderBattleship.MetaGroup METAGROUP = new AsteroidBloodRaidersCommanderBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersCommanderBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersCommanderBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersCommanderBattleship.yaml";
        private Map<String, AsteroidBloodRaidersCommanderBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersCommanderBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  849;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersCommanderBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersCommanderBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersCommanderBattleship> items;
        }
    }
}
