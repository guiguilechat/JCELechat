package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCFrigate
    extends Entity
{
    public static final NPCFrigate.MetaGroup METAGROUP = new NPCFrigate.MetaGroup();

    @Override
    public IMetaGroup<NPCFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/NPCFrigate.yaml";
        private Map<String, NPCFrigate> cache = (null);

        @Override
        public IMetaCategory<? super NPCFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1803;
        }

        @Override
        public String getName() {
            return "NPCFrigate";
        }

        @Override
        public synchronized Map<String, NPCFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCFrigate> items;
        }
    }
}
