package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCBattlecruiser
    extends Entity
{
    public final static NPCBattlecruiser.MetaGroup METAGROUP = new NPCBattlecruiser.MetaGroup();

    @Override
    public IMetaGroup<NPCBattlecruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCBattlecruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/NPCBattlecruiser.yaml";
        private Map<String, NPCBattlecruiser> cache = (null);

        @Override
        public IMetaCategory<? super NPCBattlecruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1909;
        }

        @Override
        public String getName() {
            return "NPCBattlecruiser";
        }

        @Override
        public synchronized Map<String, NPCBattlecruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCBattlecruiser> items;
        }
    }
}
