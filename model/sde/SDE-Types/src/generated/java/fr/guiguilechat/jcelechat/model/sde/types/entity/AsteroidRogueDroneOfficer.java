package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneOfficer
    extends Entity
{
    public static final AsteroidRogueDroneOfficer.MetaGroup METAGROUP = new AsteroidRogueDroneOfficer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneOfficer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneOfficer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneOfficer.yaml";
        private Map<String, AsteroidRogueDroneOfficer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneOfficer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1174;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneOfficer";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneOfficer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneOfficer> items;
        }
    }
}
