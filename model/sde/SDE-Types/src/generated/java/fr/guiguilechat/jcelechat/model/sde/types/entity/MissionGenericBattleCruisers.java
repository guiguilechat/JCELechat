package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGenericBattleCruisers
    extends Entity
{
    public static final MissionGenericBattleCruisers.MetaGroup METAGROUP = new MissionGenericBattleCruisers.MetaGroup();

    @Override
    public IMetaGroup<MissionGenericBattleCruisers> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionGenericBattleCruisers>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionGenericBattleCruisers.yaml";
        private Map<String, MissionGenericBattleCruisers> cache = (null);

        @Override
        public IMetaCategory<? super MissionGenericBattleCruisers> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  828;
        }

        @Override
        public String getName() {
            return "MissionGenericBattleCruisers";
        }

        @Override
        public synchronized Map<String, MissionGenericBattleCruisers> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionGenericBattleCruisers.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGenericBattleCruisers> types;
        }
    }
}
