package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisDreadnought
    extends Entity
{
    public static final AsteroidSerpentisDreadnought.MetaGroup METAGROUP = new AsteroidSerpentisDreadnought.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisDreadnought> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisDreadnought>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisDreadnought.yaml";
        private Map<String, AsteroidSerpentisDreadnought> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisDreadnought> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1689;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisDreadnought";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisDreadnought> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisDreadnought.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisDreadnought> items;
        }
    }
}
