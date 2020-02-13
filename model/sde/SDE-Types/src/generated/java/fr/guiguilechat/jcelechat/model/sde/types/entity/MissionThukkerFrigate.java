package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionThukkerFrigate
    extends Entity
{
    public static final MissionThukkerFrigate.MetaGroup METAGROUP = new MissionThukkerFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionThukkerFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionThukkerFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionThukkerFrigate.yaml";
        private Map<String, MissionThukkerFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionThukkerFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  826;
        }

        @Override
        public String getName() {
            return "MissionThukkerFrigate";
        }

        @Override
        public synchronized Map<String, MissionThukkerFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionThukkerFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionThukkerFrigate> items;
        }
    }
}
