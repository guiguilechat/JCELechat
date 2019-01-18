package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersFrigate
    extends Entity
{
    public static final AsteroidBloodRaidersFrigate.MetaGroup METAGROUP = new AsteroidBloodRaidersFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersFrigate.yaml";
        private Map<String, AsteroidBloodRaidersFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  557;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersFrigate> items;
        }
    }
}
