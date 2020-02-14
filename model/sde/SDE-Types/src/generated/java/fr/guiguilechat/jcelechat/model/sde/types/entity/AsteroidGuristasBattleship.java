package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasBattleship
    extends Entity
{
    public static final AsteroidGuristasBattleship.MetaGroup METAGROUP = new AsteroidGuristasBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidGuristasBattleship.yaml";
        private Map<String, AsteroidGuristasBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  560;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidGuristasBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasBattleship> types;
        }
    }
}
