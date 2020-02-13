package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelCommanderBattleCruiser
    extends Entity
{
    public static final AsteroidAngelCartelCommanderBattleCruiser.MetaGroup METAGROUP = new AsteroidAngelCartelCommanderBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelCommanderBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelCommanderBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelCommanderBattleCruiser.yaml";
        private Map<String, AsteroidAngelCartelCommanderBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelCommanderBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  793;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelCommanderBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelCommanderBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelCommanderBattleCruiser> items;
        }
    }
}
