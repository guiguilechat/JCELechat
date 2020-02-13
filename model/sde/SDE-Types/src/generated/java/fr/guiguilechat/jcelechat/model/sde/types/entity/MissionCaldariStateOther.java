package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateOther
    extends Entity
{
    public static final MissionCaldariStateOther.MetaGroup METAGROUP = new MissionCaldariStateOther.MetaGroup();

    @Override
    public IMetaGroup<MissionCaldariStateOther> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCaldariStateOther>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateOther.yaml";
        private Map<String, MissionCaldariStateOther> cache = (null);

        @Override
        public IMetaCategory<? super MissionCaldariStateOther> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  675;
        }

        @Override
        public String getName() {
            return "MissionCaldariStateOther";
        }

        @Override
        public synchronized Map<String, MissionCaldariStateOther> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateOther.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCaldariStateOther> items;
        }
    }
}
