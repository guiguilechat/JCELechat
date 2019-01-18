package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicCruiser
    extends Entity
{
    public static final MissionMinmatarRepublicCruiser.MetaGroup METAGROUP = new MissionMinmatarRepublicCruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionMinmatarRepublicCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionMinmatarRepublicCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicCruiser.yaml";
        private Map<String, MissionMinmatarRepublicCruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionMinmatarRepublicCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  705;
        }

        @Override
        public String getName() {
            return "MissionMinmatarRepublicCruiser";
        }

        @Override
        public synchronized Map<String, MissionMinmatarRepublicCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionMinmatarRepublicCruiser> items;
        }
    }
}
