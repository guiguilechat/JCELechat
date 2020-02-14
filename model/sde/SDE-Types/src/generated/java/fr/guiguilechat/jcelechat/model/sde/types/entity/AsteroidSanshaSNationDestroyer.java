package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
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
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSanshaSNationDestroyer.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSanshaSNationDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationDestroyer> types;
        }
    }
}
