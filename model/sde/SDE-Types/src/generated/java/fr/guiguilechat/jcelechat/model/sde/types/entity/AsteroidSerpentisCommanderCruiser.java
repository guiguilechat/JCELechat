package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderCruiser
    extends Entity
{
    public static final AsteroidSerpentisCommanderCruiser.MetaGroup METAGROUP = new AsteroidSerpentisCommanderCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisCommanderCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisCommanderCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSerpentisCommanderCruiser.yaml";
        private Map<String, AsteroidSerpentisCommanderCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisCommanderCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  812;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisCommanderCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisCommanderCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSerpentisCommanderCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisCommanderCruiser> types;
        }
    }
}
