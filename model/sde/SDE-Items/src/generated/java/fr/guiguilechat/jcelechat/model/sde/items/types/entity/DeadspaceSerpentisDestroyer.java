package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSerpentisDestroyer
    extends Entity
{
    public final static DeadspaceSerpentisDestroyer.MetaGroup METAGROUP = new DeadspaceSerpentisDestroyer.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSerpentisDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSerpentisDestroyer>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSerpentisDestroyer.yaml";
        private Map<String, DeadspaceSerpentisDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSerpentisDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  632;
        }

        @Override
        public String getName() {
            return "DeadspaceSerpentisDestroyer";
        }

        @Override
        public synchronized Map<String, DeadspaceSerpentisDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSerpentisDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSerpentisDestroyer> items;
        }
    }
}
