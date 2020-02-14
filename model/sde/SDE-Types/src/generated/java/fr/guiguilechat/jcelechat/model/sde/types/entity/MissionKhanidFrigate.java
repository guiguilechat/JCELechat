package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionKhanidFrigate
    extends Entity
{
    public static final MissionKhanidFrigate.MetaGroup METAGROUP = new MissionKhanidFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionKhanidFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionKhanidFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionKhanidFrigate.yaml";
        private Map<String, MissionKhanidFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionKhanidFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  687;
        }

        @Override
        public String getName() {
            return "MissionKhanidFrigate";
        }

        @Override
        public synchronized Map<String, MissionKhanidFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionKhanidFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionKhanidFrigate> types;
        }
    }
}
