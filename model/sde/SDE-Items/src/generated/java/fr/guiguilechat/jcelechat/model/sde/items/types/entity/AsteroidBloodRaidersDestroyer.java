package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersDestroyer
    extends Entity
{
    public static final AsteroidBloodRaidersDestroyer.MetaGroup METAGROUP = new AsteroidBloodRaidersDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersDestroyer.yaml";
        private Map<String, AsteroidBloodRaidersDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  577;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersDestroyer> items;
        }
    }
}
