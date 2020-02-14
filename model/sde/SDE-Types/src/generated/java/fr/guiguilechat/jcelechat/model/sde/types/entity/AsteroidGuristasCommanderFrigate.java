package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderFrigate
    extends Entity
{
    public static final AsteroidGuristasCommanderFrigate.MetaGroup METAGROUP = new AsteroidGuristasCommanderFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasCommanderFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasCommanderFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidGuristasCommanderFrigate.yaml";
        private Map<String, AsteroidGuristasCommanderFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasCommanderFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  800;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasCommanderFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasCommanderFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidGuristasCommanderFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasCommanderFrigate> types;
        }
    }
}
