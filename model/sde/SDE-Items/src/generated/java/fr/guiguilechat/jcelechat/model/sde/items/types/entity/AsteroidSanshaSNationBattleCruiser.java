package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationBattleCruiser
    extends Entity
{
    public final static AsteroidSanshaSNationBattleCruiser.MetaGroup METAGROUP = new AsteroidSanshaSNationBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationBattleCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationBattleCruiser.yaml";
        private Map<String, AsteroidSanshaSNationBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  582;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationBattleCruiser> items;
        }
    }
}
