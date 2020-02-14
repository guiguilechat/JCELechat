package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StorylineBattleship
    extends Entity
{
    public static final StorylineBattleship.MetaGroup METAGROUP = new StorylineBattleship.MetaGroup();

    @Override
    public IMetaGroup<StorylineBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StorylineBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/StorylineBattleship.yaml";
        private Map<String, StorylineBattleship> cache = (null);

        @Override
        public IMetaCategory<? super StorylineBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  523;
        }

        @Override
        public String getName() {
            return "StorylineBattleship";
        }

        @Override
        public synchronized Map<String, StorylineBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StorylineBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StorylineBattleship> types;
        }
    }
}
