package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersHauler
    extends Entity
{
    public static final AsteroidBloodRaidersHauler.MetaGroup METAGROUP = new AsteroidBloodRaidersHauler.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersHauler> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersHauler>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidBloodRaidersHauler.yaml";
        private Map<String, AsteroidBloodRaidersHauler> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersHauler> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  558;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersHauler";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersHauler> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidBloodRaidersHauler.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersHauler> types;
        }
    }
}
