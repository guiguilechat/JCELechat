package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationDestroyer
    extends Entity
{
    public static final AsteroidSanshaSNationDestroyer.MetaGroup METAGROUP = new AsteroidSanshaSNationDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationDestroyer.yaml";
        private Map<String, AsteroidSanshaSNationDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  581;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationDestroyer> items;
        }
    }
}
