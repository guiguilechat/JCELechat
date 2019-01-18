package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceBloodRaidersBattleCruiser
    extends Entity
{
    public static final DeadspaceBloodRaidersBattleCruiser.MetaGroup METAGROUP = new DeadspaceBloodRaidersBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceBloodRaidersBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceBloodRaidersBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceBloodRaidersBattleCruiser.yaml";
        private Map<String, DeadspaceBloodRaidersBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceBloodRaidersBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  602;
        }

        @Override
        public String getName() {
            return "DeadspaceBloodRaidersBattleCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceBloodRaidersBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceBloodRaidersBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceBloodRaidersBattleCruiser> items;
        }
    }
}
