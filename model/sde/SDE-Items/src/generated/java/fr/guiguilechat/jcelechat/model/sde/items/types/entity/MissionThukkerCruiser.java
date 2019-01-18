package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionThukkerCruiser
    extends Entity
{
    public static final MissionThukkerCruiser.MetaGroup METAGROUP = new MissionThukkerCruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionThukkerCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionThukkerCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionThukkerCruiser.yaml";
        private Map<String, MissionThukkerCruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionThukkerCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  824;
        }

        @Override
        public String getName() {
            return "MissionThukkerCruiser";
        }

        @Override
        public synchronized Map<String, MissionThukkerCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionThukkerCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionThukkerCruiser> items;
        }
    }
}
