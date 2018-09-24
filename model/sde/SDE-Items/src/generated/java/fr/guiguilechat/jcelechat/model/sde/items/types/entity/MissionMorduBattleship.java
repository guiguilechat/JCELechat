package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMorduBattleship
    extends Entity
{
    public final static MissionMorduBattleship.MetaGroup METAGROUP = new MissionMorduBattleship.MetaGroup();

    @Override
    public IMetaGroup<MissionMorduBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionMorduBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionMorduBattleship.yaml";
        private Map<String, MissionMorduBattleship> cache = (null);

        @Override
        public IMetaCategory<? super MissionMorduBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  703;
        }

        @Override
        public String getName() {
            return "MissionMorduBattleship";
        }

        @Override
        public synchronized Map<String, MissionMorduBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionMorduBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionMorduBattleship> items;
        }
    }
}
