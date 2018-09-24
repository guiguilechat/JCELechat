package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StorylineMissionCruiser
    extends Entity
{
    public final static StorylineMissionCruiser.MetaGroup METAGROUP = new StorylineMissionCruiser.MetaGroup();

    @Override
    public IMetaGroup<StorylineMissionCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StorylineMissionCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/StorylineMissionCruiser.yaml";
        private Map<String, StorylineMissionCruiser> cache = (null);

        @Override
        public IMetaCategory<? super StorylineMissionCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  533;
        }

        @Override
        public String getName() {
            return "StorylineMissionCruiser";
        }

        @Override
        public synchronized Map<String, StorylineMissionCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StorylineMissionCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StorylineMissionCruiser> items;
        }
    }
}
