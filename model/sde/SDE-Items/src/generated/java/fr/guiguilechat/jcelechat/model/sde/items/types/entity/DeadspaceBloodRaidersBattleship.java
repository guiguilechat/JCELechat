package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceBloodRaidersBattleship
    extends Entity
{
    public final static DeadspaceBloodRaidersBattleship.MetaGroup METAGROUP = new DeadspaceBloodRaidersBattleship.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceBloodRaidersBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceBloodRaidersBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceBloodRaidersBattleship.yaml";
        private Map<String, DeadspaceBloodRaidersBattleship> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceBloodRaidersBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  603;
        }

        @Override
        public String getName() {
            return "DeadspaceBloodRaidersBattleship";
        }

        @Override
        public synchronized Map<String, DeadspaceBloodRaidersBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceBloodRaidersBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceBloodRaidersBattleship> items;
        }
    }
}
