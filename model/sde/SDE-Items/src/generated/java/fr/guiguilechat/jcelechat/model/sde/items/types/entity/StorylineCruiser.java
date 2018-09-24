package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StorylineCruiser
    extends Entity
{
    public final static StorylineCruiser.MetaGroup METAGROUP = new StorylineCruiser.MetaGroup();

    @Override
    public IMetaGroup<StorylineCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StorylineCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/StorylineCruiser.yaml";
        private Map<String, StorylineCruiser> cache = (null);

        @Override
        public IMetaCategory<? super StorylineCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  522;
        }

        @Override
        public String getName() {
            return "StorylineCruiser";
        }

        @Override
        public synchronized Map<String, StorylineCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StorylineCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StorylineCruiser> items;
        }
    }
}
