package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderCruiser
    extends Entity
{
    public static final AsteroidRogueDroneCommanderCruiser.MetaGroup METAGROUP = new AsteroidRogueDroneCommanderCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneCommanderCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneCommanderCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCommanderCruiser.yaml";
        private Map<String, AsteroidRogueDroneCommanderCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneCommanderCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  845;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneCommanderCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneCommanderCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneCommanderCruiser> items;
        }
    }
}
