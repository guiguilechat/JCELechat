package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelHauler
    extends Entity
{
    public final static AsteroidAngelCartelHauler.MetaGroup METAGROUP = new AsteroidAngelCartelHauler.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelHauler> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelHauler>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelHauler.yaml";
        private Map<String, AsteroidAngelCartelHauler> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelHauler> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  554;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelHauler";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelHauler> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelHauler.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelHauler> items;
        }
    }
}
