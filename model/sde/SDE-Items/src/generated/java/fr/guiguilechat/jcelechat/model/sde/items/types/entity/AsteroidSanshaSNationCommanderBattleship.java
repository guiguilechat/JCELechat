package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderBattleship
    extends Entity
{
    public final static AsteroidSanshaSNationCommanderBattleship.MetaGroup METAGROUP = new AsteroidSanshaSNationCommanderBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationCommanderBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationCommanderBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationCommanderBattleship.yaml";
        private Map<String, AsteroidSanshaSNationCommanderBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationCommanderBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  851;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationCommanderBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationCommanderBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationCommanderBattleship> items;
        }
    }
}
