package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateBattlecruiser
    extends Entity
{
    public final static MissionCaldariStateBattlecruiser.MetaGroup METAGROUP = new MissionCaldariStateBattlecruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionCaldariStateBattlecruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCaldariStateBattlecruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateBattlecruiser.yaml";
        private Map<String, MissionCaldariStateBattlecruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionCaldariStateBattlecruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  672;
        }

        @Override
        public String getName() {
            return "MissionCaldariStateBattlecruiser";
        }

        @Override
        public synchronized Map<String, MissionCaldariStateBattlecruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCaldariStateBattlecruiser> items;
        }
    }
}
