package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicCarrier
    extends Entity
{
    public final static MissionMinmatarRepublicCarrier.MetaGroup METAGROUP = new MissionMinmatarRepublicCarrier.MetaGroup();

    @Override
    public IMetaGroup<MissionMinmatarRepublicCarrier> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionMinmatarRepublicCarrier>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicCarrier.yaml";
        private Map<String, MissionMinmatarRepublicCarrier> cache = (null);

        @Override
        public IMetaCategory<? super MissionMinmatarRepublicCarrier> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  868;
        }

        @Override
        public String getName() {
            return "MissionMinmatarRepublicCarrier";
        }

        @Override
        public synchronized Map<String, MissionMinmatarRepublicCarrier> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicCarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionMinmatarRepublicCarrier> items;
        }
    }
}
