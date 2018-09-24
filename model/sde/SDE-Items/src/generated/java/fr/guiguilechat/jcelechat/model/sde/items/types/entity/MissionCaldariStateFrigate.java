package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateFrigate
    extends Entity
{
    public final static MissionCaldariStateFrigate.MetaGroup METAGROUP = new MissionCaldariStateFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionCaldariStateFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCaldariStateFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateFrigate.yaml";
        private Map<String, MissionCaldariStateFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionCaldariStateFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  671;
        }

        @Override
        public String getName() {
            return "MissionCaldariStateFrigate";
        }

        @Override
        public synchronized Map<String, MissionCaldariStateFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCaldariStateFrigate> items;
        }
    }
}
