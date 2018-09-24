package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCCruiser
    extends Entity
{
    public final static NPCCruiser.MetaGroup METAGROUP = new NPCCruiser.MetaGroup();

    @Override
    public IMetaGroup<NPCCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/NPCCruiser.yaml";
        private Map<String, NPCCruiser> cache = (null);

        @Override
        public IMetaCategory<? super NPCCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1813;
        }

        @Override
        public String getName() {
            return "NPCCruiser";
        }

        @Override
        public synchronized Map<String, NPCCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCCruiser> items;
        }
    }
}
