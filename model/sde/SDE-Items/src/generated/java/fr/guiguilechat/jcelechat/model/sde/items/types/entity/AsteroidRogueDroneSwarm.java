package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneSwarm
    extends Entity
{
    public final static AsteroidRogueDroneSwarm.MetaGroup METAGROUP = new AsteroidRogueDroneSwarm.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneSwarm> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneSwarm>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneSwarm.yaml";
        private Map<String, AsteroidRogueDroneSwarm> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneSwarm> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  761;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneSwarm";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneSwarm> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneSwarm.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneSwarm> items;
        }
    }
}
