package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderBattleCruiser
    extends Entity
{
    public final static AsteroidSanshaSNationCommanderBattleCruiser.MetaGroup METAGROUP = new AsteroidSanshaSNationCommanderBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationCommanderBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationCommanderBattleCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationCommanderBattleCruiser.yaml";
        private Map<String, AsteroidSanshaSNationCommanderBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationCommanderBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  807;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationCommanderBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationCommanderBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationCommanderBattleCruiser> items;
        }
    }
}
