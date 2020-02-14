package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StorylineFrigate
    extends Entity
{
    public static final StorylineFrigate.MetaGroup METAGROUP = new StorylineFrigate.MetaGroup();

    @Override
    public IMetaGroup<StorylineFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StorylineFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/StorylineFrigate.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(StorylineFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StorylineFrigate> types;
        }
    }
}
