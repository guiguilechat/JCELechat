package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelOfficer
    extends Entity
{
    public static final AsteroidAngelCartelOfficer.MetaGroup METAGROUP = new AsteroidAngelCartelOfficer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelOfficer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelOfficer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelOfficer.yaml";
        private Map<String, AsteroidAngelCartelOfficer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelOfficer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  553;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelOfficer";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelOfficer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelOfficer> items;
        }
    }
}
