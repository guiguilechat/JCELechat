package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionDrone
    extends Entity
{
    public final static MissionDrone.MetaGroup METAGROUP = new MissionDrone.MetaGroup();

    @Override
    public IMetaGroup<MissionDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionDrone>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionDrone.yaml";
        private Map<String, MissionDrone> cache = (null);

        @Override
        public IMetaCategory<? super MissionDrone> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  337;
        }

        @Override
        public String getName() {
            return "MissionDrone";
        }

        @Override
        public synchronized Map<String, MissionDrone> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionDrone> items;
        }
    }
}
