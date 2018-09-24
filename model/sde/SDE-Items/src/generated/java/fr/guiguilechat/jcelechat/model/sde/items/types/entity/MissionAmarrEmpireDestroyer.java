package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireDestroyer
    extends Entity
{
    public final static MissionAmarrEmpireDestroyer.MetaGroup METAGROUP = new MissionAmarrEmpireDestroyer.MetaGroup();

    @Override
    public IMetaGroup<MissionAmarrEmpireDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionAmarrEmpireDestroyer>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionAmarrEmpireDestroyer.yaml";
        private Map<String, MissionAmarrEmpireDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super MissionAmarrEmpireDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  669;
        }

        @Override
        public String getName() {
            return "MissionAmarrEmpireDestroyer";
        }

        @Override
        public synchronized Map<String, MissionAmarrEmpireDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionAmarrEmpireDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionAmarrEmpireDestroyer> items;
        }
    }
}
