package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderDestroyer
    extends Entity
{
    public final static AsteroidRogueDroneCommanderDestroyer.MetaGroup METAGROUP = new AsteroidRogueDroneCommanderDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneCommanderDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneCommanderDestroyer>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCommanderDestroyer.yaml";
        private Map<String, AsteroidRogueDroneCommanderDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneCommanderDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  846;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneCommanderDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneCommanderDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCommanderDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneCommanderDestroyer> items;
        }
    }
}
