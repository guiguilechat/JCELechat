package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneDestroyer
    extends Entity
{
    public final static AsteroidRogueDroneDestroyer.MetaGroup METAGROUP = new AsteroidRogueDroneDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneDestroyer>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneDestroyer.yaml";
        private Map<String, AsteroidRogueDroneDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  758;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneDestroyer> items;
        }
    }
}
