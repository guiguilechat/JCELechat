package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersBattleCruiser
    extends Entity
{
    public static final AsteroidBloodRaidersBattleCruiser.MetaGroup METAGROUP = new AsteroidBloodRaidersBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersBattleCruiser.yaml";
        private Map<String, AsteroidBloodRaidersBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  578;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersBattleCruiser> items;
        }
    }
}
