package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
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
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidAngelCartelTitan.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(AsteroidAngelCartelTitan.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelTitan> types;
        }
    }
}
