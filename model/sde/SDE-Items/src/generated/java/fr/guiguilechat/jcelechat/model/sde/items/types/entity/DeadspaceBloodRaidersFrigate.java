package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceBloodRaidersFrigate
    extends Entity
{
    public final static DeadspaceBloodRaidersFrigate.MetaGroup METAGROUP = new DeadspaceBloodRaidersFrigate.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceBloodRaidersFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceBloodRaidersFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceBloodRaidersFrigate.yaml";
        private Map<String, DeadspaceBloodRaidersFrigate> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceBloodRaidersFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  606;
        }

        @Override
        public String getName() {
            return "DeadspaceBloodRaidersFrigate";
        }

        @Override
        public synchronized Map<String, DeadspaceBloodRaidersFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceBloodRaidersFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceBloodRaidersFrigate> items;
        }
    }
}
