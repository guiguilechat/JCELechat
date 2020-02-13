package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersBattleship
    extends Entity
{
    public static final AsteroidBloodRaidersBattleship.MetaGroup METAGROUP = new AsteroidBloodRaidersBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersBattleship.yaml";
        private Map<String, AsteroidBloodRaidersBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  556;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersBattleship> items;
        }
    }
}
