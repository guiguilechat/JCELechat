package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderCruiser
    extends Entity
{
    public final static AsteroidGuristasCommanderCruiser.MetaGroup METAGROUP = new AsteroidGuristasCommanderCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasCommanderCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasCommanderCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCommanderCruiser.yaml";
        private Map<String, AsteroidGuristasCommanderCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasCommanderCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  798;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasCommanderCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasCommanderCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasCommanderCruiser> items;
        }
    }
}
