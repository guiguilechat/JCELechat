package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderDestroyer
    extends Entity
{
    public final static AsteroidSanshaSNationCommanderDestroyer.MetaGroup METAGROUP = new AsteroidSanshaSNationCommanderDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationCommanderDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationCommanderDestroyer>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationCommanderDestroyer.yaml";
        private Map<String, AsteroidSanshaSNationCommanderDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationCommanderDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  809;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationCommanderDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationCommanderDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationCommanderDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationCommanderDestroyer> items;
        }
    }
}
