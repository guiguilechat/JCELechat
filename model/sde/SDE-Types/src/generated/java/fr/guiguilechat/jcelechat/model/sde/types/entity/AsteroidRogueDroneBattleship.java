package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneBattleship
    extends Entity
{
    public static final AsteroidRogueDroneBattleship.MetaGroup METAGROUP = new AsteroidRogueDroneBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneBattleship.yaml";
        private Map<String, AsteroidRogueDroneBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  756;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneBattleship> items;
        }
    }
}
