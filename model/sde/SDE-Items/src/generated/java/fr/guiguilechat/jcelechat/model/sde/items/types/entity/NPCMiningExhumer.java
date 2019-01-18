package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCMiningExhumer
    extends Entity
{
    public static final NPCMiningExhumer.MetaGroup METAGROUP = new NPCMiningExhumer.MetaGroup();

    @Override
    public IMetaGroup<NPCMiningExhumer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCMiningExhumer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/NPCMiningExhumer.yaml";
        private Map<String, NPCMiningExhumer> cache = (null);

        @Override
        public IMetaCategory<? super NPCMiningExhumer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1766;
        }

        @Override
        public String getName() {
            return "NPCMiningExhumer";
        }

        @Override
        public synchronized Map<String, NPCMiningExhumer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCMiningExhumer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCMiningExhumer> items;
        }
    }
}
