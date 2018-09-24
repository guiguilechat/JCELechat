package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCBattleship
    extends Entity
{
    public final static NPCBattleship.MetaGroup METAGROUP = new NPCBattleship.MetaGroup();

    @Override
    public IMetaGroup<NPCBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/NPCBattleship.yaml";
        private Map<String, NPCBattleship> cache = (null);

        @Override
        public IMetaCategory<? super NPCBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1814;
        }

        @Override
        public String getName() {
            return "NPCBattleship";
        }

        @Override
        public synchronized Map<String, NPCBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCBattleship> items;
        }
    }
}
