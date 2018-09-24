package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderDestroyer
    extends Entity
{
    public final static AsteroidSerpentisCommanderDestroyer.MetaGroup METAGROUP = new AsteroidSerpentisCommanderDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisCommanderDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisCommanderDestroyer>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCommanderDestroyer.yaml";
        private Map<String, AsteroidSerpentisCommanderDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisCommanderDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  813;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisCommanderDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisCommanderDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCommanderDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisCommanderDestroyer> items;
        }
    }
}
