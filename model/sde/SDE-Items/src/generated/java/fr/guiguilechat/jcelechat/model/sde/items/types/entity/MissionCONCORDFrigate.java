package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCONCORDFrigate
    extends Entity
{
    public static final MissionCONCORDFrigate.MetaGroup METAGROUP = new MissionCONCORDFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionCONCORDFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCONCORDFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionCONCORDFrigate.yaml";
        private Map<String, MissionCONCORDFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionCONCORDFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  693;
        }

        @Override
        public String getName() {
            return "MissionCONCORDFrigate";
        }

        @Override
        public synchronized Map<String, MissionCONCORDFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionCONCORDFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCONCORDFrigate> items;
        }
    }
}
