package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
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
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicDestroyer.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionMinmatarRepublicDestroyer> items;
        }
    }
}
