package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelCommanderBattleship
    extends Entity
{
    public static final AsteroidAngelCartelCommanderBattleship.MetaGroup METAGROUP = new AsteroidAngelCartelCommanderBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelCommanderBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelCommanderBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelCommanderBattleship.yaml";
        private Map<String, AsteroidAngelCartelCommanderBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelCommanderBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  848;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelCommanderBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelCommanderBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelCommanderBattleship> items;
        }
    }
}
