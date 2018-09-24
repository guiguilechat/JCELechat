package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelDreadnought
    extends Entity
{
    public final static AsteroidAngelCartelDreadnought.MetaGroup METAGROUP = new AsteroidAngelCartelDreadnought.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelDreadnought> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelDreadnought>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelDreadnought.yaml";
        private Map<String, AsteroidAngelCartelDreadnought> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelDreadnought> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1681;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelDreadnought";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelDreadnought> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelDreadnought.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelDreadnought> items;
        }
    }
}
