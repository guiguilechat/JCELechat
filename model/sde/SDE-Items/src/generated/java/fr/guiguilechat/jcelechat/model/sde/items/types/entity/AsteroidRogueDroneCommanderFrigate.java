package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderFrigate
    extends Entity
{
    public static final AsteroidRogueDroneCommanderFrigate.MetaGroup METAGROUP = new AsteroidRogueDroneCommanderFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneCommanderFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneCommanderFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCommanderFrigate.yaml";
        private Map<String, AsteroidRogueDroneCommanderFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneCommanderFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  847;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneCommanderFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneCommanderFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneCommanderFrigate> items;
        }
    }
}
