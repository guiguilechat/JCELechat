package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class LargeCollidableShip
    extends Entity
{
    public static final LargeCollidableShip.MetaGroup METAGROUP = new LargeCollidableShip.MetaGroup();

    @Override
    public IMetaGroup<LargeCollidableShip> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LargeCollidableShip>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/LargeCollidableShip.yaml";
        private Map<String, LargeCollidableShip> cache = (null);

        @Override
        public IMetaCategory<? super LargeCollidableShip> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  784;
        }

        @Override
        public String getName() {
            return "LargeCollidableShip";
        }

        @Override
        public synchronized Map<String, LargeCollidableShip> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(LargeCollidableShip.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, LargeCollidableShip> items;
        }
    }
}
