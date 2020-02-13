package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaiderTitan
    extends Entity
{
    public static final AsteroidBloodRaiderTitan.MetaGroup METAGROUP = new AsteroidBloodRaiderTitan.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaiderTitan> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaiderTitan>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaiderTitan.yaml";
        private Map<String, AsteroidBloodRaiderTitan> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaiderTitan> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1684;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaiderTitan";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaiderTitan> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaiderTitan.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaiderTitan> items;
        }
    }
}
