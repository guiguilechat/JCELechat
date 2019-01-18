package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCruiser
    extends Entity
{
    public static final AsteroidRogueDroneCruiser.MetaGroup METAGROUP = new AsteroidRogueDroneCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCruiser.yaml";
        private Map<String, AsteroidRogueDroneCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  757;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneCruiser> items;
        }
    }
}
