package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderBattleship
    extends Entity
{
    public final static AsteroidSerpentisCommanderBattleship.MetaGroup METAGROUP = new AsteroidSerpentisCommanderBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisCommanderBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisCommanderBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCommanderBattleship.yaml";
        private Map<String, AsteroidSerpentisCommanderBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisCommanderBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  852;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisCommanderBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisCommanderBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisCommanderBattleship> items;
        }
    }
}
