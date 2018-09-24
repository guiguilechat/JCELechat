package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireBattlecruiser
    extends Entity
{
    public final static MissionAmarrEmpireBattlecruiser.MetaGroup METAGROUP = new MissionAmarrEmpireBattlecruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionAmarrEmpireBattlecruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionAmarrEmpireBattlecruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionAmarrEmpireBattlecruiser.yaml";
        private Map<String, MissionAmarrEmpireBattlecruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionAmarrEmpireBattlecruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  666;
        }

        @Override
        public String getName() {
            return "MissionAmarrEmpireBattlecruiser";
        }

        @Override
        public synchronized Map<String, MissionAmarrEmpireBattlecruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionAmarrEmpireBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionAmarrEmpireBattlecruiser> items;
        }
    }
}
