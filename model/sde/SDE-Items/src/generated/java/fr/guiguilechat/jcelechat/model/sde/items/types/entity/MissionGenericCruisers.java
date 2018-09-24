package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGenericCruisers
    extends Entity
{
    public final static MissionGenericCruisers.MetaGroup METAGROUP = new MissionGenericCruisers.MetaGroup();

    @Override
    public IMetaGroup<MissionGenericCruisers> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionGenericCruisers>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionGenericCruisers.yaml";
        private Map<String, MissionGenericCruisers> cache = (null);

        @Override
        public IMetaCategory<? super MissionGenericCruisers> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  817;
        }

        @Override
        public String getName() {
            return "MissionGenericCruisers";
        }

        @Override
        public synchronized Map<String, MissionGenericCruisers> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionGenericCruisers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGenericCruisers> items;
        }
    }
}
