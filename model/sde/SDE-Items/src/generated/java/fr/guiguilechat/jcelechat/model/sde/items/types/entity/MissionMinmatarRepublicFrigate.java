package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicFrigate
    extends Entity
{
    public final static MissionMinmatarRepublicFrigate.MetaGroup METAGROUP = new MissionMinmatarRepublicFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionMinmatarRepublicFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionMinmatarRepublicFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicFrigate.yaml";
        private Map<String, MissionMinmatarRepublicFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionMinmatarRepublicFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  683;
        }

        @Override
        public String getName() {
            return "MissionMinmatarRepublicFrigate";
        }

        @Override
        public synchronized Map<String, MissionMinmatarRepublicFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionMinmatarRepublicFrigate> items;
        }
    }
}
