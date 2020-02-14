package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
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
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidBloodRaidersDestroyer.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(AsteroidBloodRaidersDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersDestroyer> types;
        }
    }
}
