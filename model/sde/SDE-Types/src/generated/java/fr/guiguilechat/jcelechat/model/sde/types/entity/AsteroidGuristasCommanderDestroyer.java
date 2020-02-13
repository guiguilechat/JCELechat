package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderDestroyer
    extends Entity
{
    public static final AsteroidGuristasCommanderDestroyer.MetaGroup METAGROUP = new AsteroidGuristasCommanderDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasCommanderDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasCommanderDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCommanderDestroyer.yaml";
        private Map<String, AsteroidGuristasCommanderDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasCommanderDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  799;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasCommanderDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasCommanderDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCommanderDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasCommanderDestroyer> items;
        }
    }
}
