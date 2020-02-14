package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateFrigate
    extends Entity
{
    public static final MissionCaldariStateFrigate.MetaGroup METAGROUP = new MissionCaldariStateFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionCaldariStateFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCaldariStateFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionCaldariStateFrigate.yaml";
        private Map<String, MissionCaldariStateFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionCaldariStateFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  671;
        }

        @Override
        public String getName() {
            return "MissionCaldariStateFrigate";
        }

        @Override
        public synchronized Map<String, MissionCaldariStateFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionCaldariStateFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCaldariStateFrigate> types;
        }
    }
}
