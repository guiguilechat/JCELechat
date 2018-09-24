package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSerpentisBattleship
    extends Entity
{
    public final static DeadspaceSerpentisBattleship.MetaGroup METAGROUP = new DeadspaceSerpentisBattleship.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSerpentisBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSerpentisBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSerpentisBattleship.yaml";
        private Map<String, DeadspaceSerpentisBattleship> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSerpentisBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  630;
        }

        @Override
        public String getName() {
            return "DeadspaceSerpentisBattleship";
        }

        @Override
        public synchronized Map<String, DeadspaceSerpentisBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSerpentisBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSerpentisBattleship> items;
        }
    }
}
