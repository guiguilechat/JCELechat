package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersOfficer
    extends Entity
{
    public static final AsteroidBloodRaidersOfficer.MetaGroup METAGROUP = new AsteroidBloodRaidersOfficer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersOfficer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersOfficer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersOfficer.yaml";
        private Map<String, AsteroidBloodRaidersOfficer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersOfficer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  559;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersOfficer";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersOfficer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersOfficer> items;
        }
    }
}
