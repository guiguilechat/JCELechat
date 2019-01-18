package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSerpentisBattleCruiser
    extends Entity
{
    public static final DeadspaceSerpentisBattleCruiser.MetaGroup METAGROUP = new DeadspaceSerpentisBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSerpentisBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSerpentisBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSerpentisBattleCruiser.yaml";
        private Map<String, DeadspaceSerpentisBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSerpentisBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  629;
        }

        @Override
        public String getName() {
            return "DeadspaceSerpentisBattleCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceSerpentisBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSerpentisBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSerpentisBattleCruiser> items;
        }
    }
}
