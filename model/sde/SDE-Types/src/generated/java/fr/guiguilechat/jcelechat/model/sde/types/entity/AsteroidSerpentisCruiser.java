package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCruiser
    extends Entity
{
    public static final AsteroidSerpentisCruiser.MetaGroup METAGROUP = new AsteroidSerpentisCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCruiser.yaml";
        private Map<String, AsteroidSerpentisCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  571;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisCruiser> items;
        }
    }
}
