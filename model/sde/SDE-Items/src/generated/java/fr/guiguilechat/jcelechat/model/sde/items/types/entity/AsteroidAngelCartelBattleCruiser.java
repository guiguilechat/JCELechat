package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelBattleCruiser
    extends Entity
{
    public final static AsteroidAngelCartelBattleCruiser.MetaGroup METAGROUP = new AsteroidAngelCartelBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelBattleCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelBattleCruiser.yaml";
        private Map<String, AsteroidAngelCartelBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  576;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelBattleCruiser> items;
        }
    }
}