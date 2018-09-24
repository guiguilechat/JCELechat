package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCMiningBarge
    extends Entity
{
    public final static NPCMiningBarge.MetaGroup METAGROUP = new NPCMiningBarge.MetaGroup();

    @Override
    public IMetaGroup<NPCMiningBarge> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCMiningBarge>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/NPCMiningBarge.yaml";
        private Map<String, NPCMiningBarge> cache = (null);

        @Override
        public IMetaCategory<? super NPCMiningBarge> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1765;
        }

        @Override
        public String getName() {
            return "NPCMiningBarge";
        }

        @Override
        public synchronized Map<String, NPCMiningBarge> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCMiningBarge.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCMiningBarge> items;
        }
    }
}
