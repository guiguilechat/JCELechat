package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasBattleCruiser
    extends Entity
{
    public static final AsteroidGuristasBattleCruiser.MetaGroup METAGROUP = new AsteroidGuristasBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasBattleCruiser.yaml";
        private Map<String, AsteroidGuristasBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  580;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasBattleCruiser> items;
        }
    }
}
