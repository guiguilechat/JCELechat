package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneBattleCruiser
    extends Entity
{
    public final static AsteroidRogueDroneBattleCruiser.MetaGroup METAGROUP = new AsteroidRogueDroneBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneBattleCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneBattleCruiser.yaml";
        private Map<String, AsteroidRogueDroneBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  755;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneBattleCruiser> items;
        }
    }
}
