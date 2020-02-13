package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionKhanidCruiser
    extends Entity
{
    public static final MissionKhanidCruiser.MetaGroup METAGROUP = new MissionKhanidCruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionKhanidCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionKhanidCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionKhanidCruiser.yaml";
        private Map<String, MissionKhanidCruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionKhanidCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  689;
        }

        @Override
        public String getName() {
            return "MissionKhanidCruiser";
        }

        @Override
        public synchronized Map<String, MissionKhanidCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionKhanidCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionKhanidCruiser> items;
        }
    }
}
