package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderFrigate
    extends Entity
{
    public static final AsteroidSerpentisCommanderFrigate.MetaGroup METAGROUP = new AsteroidSerpentisCommanderFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisCommanderFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisCommanderFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCommanderFrigate.yaml";
        private Map<String, AsteroidSerpentisCommanderFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisCommanderFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  814;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisCommanderFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisCommanderFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisCommanderFrigate> items;
        }
    }
}
