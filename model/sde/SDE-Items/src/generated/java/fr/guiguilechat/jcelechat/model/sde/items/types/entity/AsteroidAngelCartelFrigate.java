package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelFrigate
    extends Entity
{
    public static final AsteroidAngelCartelFrigate.MetaGroup METAGROUP = new AsteroidAngelCartelFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelFrigate.yaml";
        private Map<String, AsteroidAngelCartelFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  550;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelFrigate> items;
        }
    }
}
