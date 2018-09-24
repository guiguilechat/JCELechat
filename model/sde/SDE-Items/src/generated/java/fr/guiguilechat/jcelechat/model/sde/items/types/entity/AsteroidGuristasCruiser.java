package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCruiser
    extends Entity
{
    public final static AsteroidGuristasCruiser.MetaGroup METAGROUP = new AsteroidGuristasCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCruiser.yaml";
        private Map<String, AsteroidGuristasCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  561;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasCruiser> items;
        }
    }
}
