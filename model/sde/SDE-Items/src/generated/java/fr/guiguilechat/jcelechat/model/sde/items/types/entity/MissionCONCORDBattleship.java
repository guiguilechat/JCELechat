package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCONCORDBattleship
    extends Entity
{
    public final static MissionCONCORDBattleship.MetaGroup METAGROUP = new MissionCONCORDBattleship.MetaGroup();

    @Override
    public IMetaGroup<MissionCONCORDBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCONCORDBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionCONCORDBattleship.yaml";
        private Map<String, MissionCONCORDBattleship> cache = (null);

        @Override
        public IMetaCategory<? super MissionCONCORDBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  697;
        }

        @Override
        public String getName() {
            return "MissionCONCORDBattleship";
        }

        @Override
        public synchronized Map<String, MissionCONCORDBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionCONCORDBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCONCORDBattleship> items;
        }
    }
}
