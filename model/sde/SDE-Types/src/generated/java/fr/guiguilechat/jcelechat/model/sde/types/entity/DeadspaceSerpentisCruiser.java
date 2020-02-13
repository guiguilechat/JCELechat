package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSerpentisCruiser
    extends Entity
{
    public static final DeadspaceSerpentisCruiser.MetaGroup METAGROUP = new DeadspaceSerpentisCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSerpentisCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSerpentisCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSerpentisCruiser.yaml";
        private Map<String, DeadspaceSerpentisCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSerpentisCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  631;
        }

        @Override
        public String getName() {
            return "DeadspaceSerpentisCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceSerpentisCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSerpentisCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSerpentisCruiser> items;
        }
    }
}
