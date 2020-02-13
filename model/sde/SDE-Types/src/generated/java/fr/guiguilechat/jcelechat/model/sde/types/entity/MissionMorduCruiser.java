package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMorduCruiser
    extends Entity
{
    public static final MissionMorduCruiser.MetaGroup METAGROUP = new MissionMorduCruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionMorduCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionMorduCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionMorduCruiser.yaml";
        private Map<String, MissionMorduCruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionMorduCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  701;
        }

        @Override
        public String getName() {
            return "MissionMorduCruiser";
        }

        @Override
        public synchronized Map<String, MissionMorduCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionMorduCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionMorduCruiser> items;
        }
    }
}
