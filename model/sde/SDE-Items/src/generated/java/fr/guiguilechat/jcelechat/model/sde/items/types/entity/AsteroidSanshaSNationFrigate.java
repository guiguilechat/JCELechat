package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationFrigate
    extends Entity
{
    public final static AsteroidSanshaSNationFrigate.MetaGroup METAGROUP = new AsteroidSanshaSNationFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationFrigate.yaml";
        private Map<String, AsteroidSanshaSNationFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  567;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationFrigate> items;
        }
    }
}
