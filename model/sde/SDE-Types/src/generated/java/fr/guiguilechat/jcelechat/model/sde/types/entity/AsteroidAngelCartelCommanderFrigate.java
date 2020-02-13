package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelCommanderFrigate
    extends Entity
{
    public static final AsteroidAngelCartelCommanderFrigate.MetaGroup METAGROUP = new AsteroidAngelCartelCommanderFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelCommanderFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelCommanderFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelCommanderFrigate.yaml";
        private Map<String, AsteroidAngelCartelCommanderFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelCommanderFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  789;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelCommanderFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelCommanderFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelCommanderFrigate> items;
        }
    }
}
