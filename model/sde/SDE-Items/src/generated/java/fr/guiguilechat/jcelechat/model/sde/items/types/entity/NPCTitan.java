package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCTitan
    extends Entity
{
    public final static NPCTitan.MetaGroup METAGROUP = new NPCTitan.MetaGroup();

    @Override
    public IMetaGroup<NPCTitan> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCTitan>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/NPCTitan.yaml";
        private Map<String, NPCTitan> cache = (null);

        @Override
        public IMetaCategory<? super NPCTitan> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1878;
        }

        @Override
        public String getName() {
            return "NPCTitan";
        }

        @Override
        public synchronized Map<String, NPCTitan> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCTitan.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCTitan> items;
        }
    }
}
