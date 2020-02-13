package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelDestroyer
    extends Entity
{
    public static final AsteroidAngelCartelDestroyer.MetaGroup METAGROUP = new AsteroidAngelCartelDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelDestroyer.yaml";
        private Map<String, AsteroidAngelCartelDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  575;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelDestroyer> items;
        }
    }
}
