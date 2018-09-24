package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasDestroyer
    extends Entity
{
    public final static AsteroidGuristasDestroyer.MetaGroup METAGROUP = new AsteroidGuristasDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasDestroyer>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasDestroyer.yaml";
        private Map<String, AsteroidGuristasDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  579;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasDestroyer> items;
        }
    }
}
