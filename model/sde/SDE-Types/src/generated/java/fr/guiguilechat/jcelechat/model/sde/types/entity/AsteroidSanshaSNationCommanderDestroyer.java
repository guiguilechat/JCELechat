package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderDestroyer
    extends Entity
{
    public static final AsteroidSanshaSNationCommanderDestroyer.MetaGroup METAGROUP = new AsteroidSanshaSNationCommanderDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationCommanderDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationCommanderDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSanshaSNationCommanderDestroyer.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSanshaSNationCommanderDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationCommanderDestroyer> types;
        }
    }
}
