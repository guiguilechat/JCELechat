package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicBattlecruiser
    extends Entity
{
    public static final MissionMinmatarRepublicBattlecruiser.MetaGroup METAGROUP = new MissionMinmatarRepublicBattlecruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionMinmatarRepublicBattlecruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionMinmatarRepublicBattlecruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicBattlecruiser.yaml";
        private Map<String, MissionMinmatarRepublicBattlecruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionMinmatarRepublicBattlecruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  685;
        }

        @Override
        public String getName() {
            return "MissionMinmatarRepublicBattlecruiser";
        }

        @Override
        public synchronized Map<String, MissionMinmatarRepublicBattlecruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionMinmatarRepublicBattlecruiser> items;
        }
    }
}
