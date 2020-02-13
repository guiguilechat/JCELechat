package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasHauler
    extends Entity
{
    public static final AsteroidGuristasHauler.MetaGroup METAGROUP = new AsteroidGuristasHauler.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasHauler> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasHauler>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasHauler.yaml";
        private Map<String, AsteroidGuristasHauler> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasHauler> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  563;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasHauler";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasHauler> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasHauler.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasHauler> items;
        }
    }
}
