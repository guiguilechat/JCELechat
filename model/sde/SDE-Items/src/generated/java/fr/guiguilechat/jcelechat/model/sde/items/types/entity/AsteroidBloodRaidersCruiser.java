package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCruiser
    extends Entity
{
    public static final AsteroidBloodRaidersCruiser.MetaGroup METAGROUP = new AsteroidBloodRaidersCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersCruiser.yaml";
        private Map<String, AsteroidBloodRaidersCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  555;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersCruiser> items;
        }
    }
}
