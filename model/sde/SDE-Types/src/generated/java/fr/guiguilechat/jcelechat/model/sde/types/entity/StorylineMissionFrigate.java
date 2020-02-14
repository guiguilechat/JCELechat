package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StorylineMissionFrigate
    extends Entity
{
    public static final StorylineMissionFrigate.MetaGroup METAGROUP = new StorylineMissionFrigate.MetaGroup();

    @Override
    public IMetaGroup<StorylineMissionFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StorylineMissionFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/StorylineMissionFrigate.yaml";
        private Map<String, StorylineMissionFrigate> cache = (null);

        @Override
        public IMetaCategory<? super StorylineMissionFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  527;
        }

        @Override
        public String getName() {
            return "StorylineMissionFrigate";
        }

        @Override
        public synchronized Map<String, StorylineMissionFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StorylineMissionFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StorylineMissionFrigate> types;
        }
    }
}
