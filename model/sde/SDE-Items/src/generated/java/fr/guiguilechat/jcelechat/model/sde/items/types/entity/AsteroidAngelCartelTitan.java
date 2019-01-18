package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelTitan
    extends Entity
{
    public static final AsteroidAngelCartelTitan.MetaGroup METAGROUP = new AsteroidAngelCartelTitan.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelTitan> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelTitan>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelTitan.yaml";
        private Map<String, AsteroidAngelCartelTitan> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelTitan> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1682;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelTitan";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelTitan> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelTitan.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelTitan> items;
        }
    }
}
