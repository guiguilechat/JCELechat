package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCruiser
    extends Entity
{
    public static final AsteroidGuristasCruiser.MetaGroup METAGROUP = new AsteroidGuristasCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidGuristasCruiser.yaml";
        private Map<String, AsteroidGuristasCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  561;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidGuristasCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasCruiser> types;
        }
    }
}
