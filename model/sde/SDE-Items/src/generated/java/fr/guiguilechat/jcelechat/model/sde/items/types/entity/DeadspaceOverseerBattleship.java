package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerBattleship
    extends Entity
{
    public final static DeadspaceOverseerBattleship.MetaGroup METAGROUP = new DeadspaceOverseerBattleship.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceOverseerBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceOverseerBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceOverseerBattleship.yaml";
        private Map<String, DeadspaceOverseerBattleship> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceOverseerBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  821;
        }

        @Override
        public String getName() {
            return "DeadspaceOverseerBattleship";
        }

        @Override
        public synchronized Map<String, DeadspaceOverseerBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceOverseerBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceOverseerBattleship> items;
        }
    }
}
