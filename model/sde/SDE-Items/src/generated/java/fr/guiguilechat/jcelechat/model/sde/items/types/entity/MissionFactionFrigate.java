package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionFrigate
    extends Entity
{
    public final static MissionFactionFrigate.MetaGroup METAGROUP = new MissionFactionFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionFactionFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionFactionFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionFactionFrigate.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionFactionFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionFactionFrigate> items;
        }
    }
}
