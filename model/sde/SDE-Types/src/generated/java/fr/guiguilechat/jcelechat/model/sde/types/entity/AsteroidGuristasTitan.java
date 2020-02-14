package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasTitan
    extends Entity
{
    public static final AsteroidGuristasTitan.MetaGroup METAGROUP = new AsteroidGuristasTitan.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasTitan> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasTitan>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidGuristasTitan.yaml";
        private Map<String, AsteroidGuristasTitan> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasTitan> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1686;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasTitan";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasTitan> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidGuristasTitan.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasTitan> types;
        }
    }
}
