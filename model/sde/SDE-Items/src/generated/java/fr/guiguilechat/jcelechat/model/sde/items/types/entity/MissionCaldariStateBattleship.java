package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateBattleship
    extends Entity
{
    public final static MissionCaldariStateBattleship.MetaGroup METAGROUP = new MissionCaldariStateBattleship.MetaGroup();

    @Override
    public IMetaGroup<MissionCaldariStateBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCaldariStateBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateBattleship.yaml";
        private Map<String, MissionCaldariStateBattleship> cache = (null);

        @Override
        public IMetaCategory<? super MissionCaldariStateBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  674;
        }

        @Override
        public String getName() {
            return "MissionCaldariStateBattleship";
        }

        @Override
        public synchronized Map<String, MissionCaldariStateBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCaldariStateBattleship> items;
        }
    }
}
