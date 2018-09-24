package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderFrigate
    extends Entity
{
    public final static AsteroidGuristasCommanderFrigate.MetaGroup METAGROUP = new AsteroidGuristasCommanderFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasCommanderFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasCommanderFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCommanderFrigate.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasCommanderFrigate> items;
        }
    }
}
