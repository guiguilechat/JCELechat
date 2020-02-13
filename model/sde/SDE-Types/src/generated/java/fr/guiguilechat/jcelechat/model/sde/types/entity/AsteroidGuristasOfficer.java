package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasOfficer
    extends Entity
{
    public static final AsteroidGuristasOfficer.MetaGroup METAGROUP = new AsteroidGuristasOfficer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasOfficer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasOfficer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasOfficer.yaml";
        private Map<String, AsteroidGuristasOfficer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasOfficer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  564;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasOfficer";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasOfficer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasOfficer> items;
        }
    }
}
