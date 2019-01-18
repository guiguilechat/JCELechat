package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelCruiser
    extends Entity
{
    public static final AsteroidAngelCartelCruiser.MetaGroup METAGROUP = new AsteroidAngelCartelCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelCruiser.yaml";
        private Map<String, AsteroidAngelCartelCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  551;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelCruiser> items;
        }
    }
}
