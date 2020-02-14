package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionFrigate
    extends Entity
{
    public static final MissionFactionFrigate.MetaGroup METAGROUP = new MissionFactionFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionFactionFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionFactionFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionFactionFrigate.yaml";
        private Map<String, MissionFactionFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionFactionFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1007;
        }

        @Override
        public String getName() {
            return "MissionFactionFrigate";
        }

        @Override
        public synchronized Map<String, MissionFactionFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionFactionFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionFactionFrigate> types;
        }
    }
}
