package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StorylineFrigate
    extends Entity
{
    public final static StorylineFrigate.MetaGroup METAGROUP = new StorylineFrigate.MetaGroup();

    @Override
    public IMetaGroup<StorylineFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StorylineFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/StorylineFrigate.yaml";
        private Map<String, StorylineFrigate> cache = (null);

        @Override
        public IMetaCategory<? super StorylineFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  520;
        }

        @Override
        public String getName() {
            return "StorylineFrigate";
        }

        @Override
        public synchronized Map<String, StorylineFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StorylineFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StorylineFrigate> items;
        }
    }
}
