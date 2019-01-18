package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGenericSupercarrier
    extends Entity
{
    public static final MissionGenericSupercarrier.MetaGroup METAGROUP = new MissionGenericSupercarrier.MetaGroup();

    @Override
    public IMetaGroup<MissionGenericSupercarrier> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionGenericSupercarrier>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionGenericSupercarrier.yaml";
        private Map<String, MissionGenericSupercarrier> cache = (null);

        @Override
        public IMetaCategory<? super MissionGenericSupercarrier> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1465;
        }

        @Override
        public String getName() {
            return "MissionGenericSupercarrier";
        }

        @Override
        public synchronized Map<String, MissionGenericSupercarrier> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionGenericSupercarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGenericSupercarrier> items;
        }
    }
}
