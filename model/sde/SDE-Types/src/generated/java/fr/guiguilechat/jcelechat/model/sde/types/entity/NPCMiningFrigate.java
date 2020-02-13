package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCMiningFrigate
    extends Entity
{
    public static final NPCMiningFrigate.MetaGroup METAGROUP = new NPCMiningFrigate.MetaGroup();

    @Override
    public IMetaGroup<NPCMiningFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCMiningFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/NPCMiningFrigate.yaml";
        private Map<String, NPCMiningFrigate> cache = (null);

        @Override
        public IMetaCategory<? super NPCMiningFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1764;
        }

        @Override
        public String getName() {
            return "NPCMiningFrigate";
        }

        @Override
        public synchronized Map<String, NPCMiningFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCMiningFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCMiningFrigate> items;
        }
    }
}
