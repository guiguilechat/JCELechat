package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceBloodRaidersCruiser
    extends Entity
{
    public final static DeadspaceBloodRaidersCruiser.MetaGroup METAGROUP = new DeadspaceBloodRaidersCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceBloodRaidersCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceBloodRaidersCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceBloodRaidersCruiser.yaml";
        private Map<String, DeadspaceBloodRaidersCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceBloodRaidersCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  604;
        }

        @Override
        public String getName() {
            return "DeadspaceBloodRaidersCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceBloodRaidersCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceBloodRaidersCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceBloodRaidersCruiser> items;
        }
    }
}
