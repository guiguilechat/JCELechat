package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateCruiser
    extends Entity
{
    public static final MissionCaldariStateCruiser.MetaGroup METAGROUP = new MissionCaldariStateCruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionCaldariStateCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCaldariStateCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateCruiser.yaml";
        private Map<String, MissionCaldariStateCruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionCaldariStateCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  673;
        }

        @Override
        public String getName() {
            return "MissionCaldariStateCruiser";
        }

        @Override
        public synchronized Map<String, MissionCaldariStateCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCaldariStateCruiser> items;
        }
    }
}
