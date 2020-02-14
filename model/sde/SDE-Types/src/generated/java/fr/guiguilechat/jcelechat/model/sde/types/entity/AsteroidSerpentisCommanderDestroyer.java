package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderDestroyer
    extends Entity
{
    public static final AsteroidSerpentisCommanderDestroyer.MetaGroup METAGROUP = new AsteroidSerpentisCommanderDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisCommanderDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisCommanderDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSerpentisCommanderDestroyer.yaml";
        private Map<String, AsteroidSerpentisCommanderDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisCommanderDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  813;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisCommanderDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisCommanderDestroyer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSerpentisCommanderDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisCommanderDestroyer> types;
        }
    }
}
