package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicDestroyer
    extends Entity
{
    public static final MissionMinmatarRepublicDestroyer.MetaGroup METAGROUP = new MissionMinmatarRepublicDestroyer.MetaGroup();

    @Override
    public IMetaGroup<MissionMinmatarRepublicDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionMinmatarRepublicDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionMinmatarRepublicDestroyer.yaml";
        private Map<String, MissionMinmatarRepublicDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super MissionMinmatarRepublicDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  684;
        }

        @Override
        public String getName() {
            return "MissionMinmatarRepublicDestroyer";
        }

        @Override
        public synchronized Map<String, MissionMinmatarRepublicDestroyer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionMinmatarRepublicDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionMinmatarRepublicDestroyer> types;
        }
    }
}
