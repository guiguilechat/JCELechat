package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderBattleCruiser
    extends Entity
{
    public static final AsteroidSerpentisCommanderBattleCruiser.MetaGroup METAGROUP = new AsteroidSerpentisCommanderBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisCommanderBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisCommanderBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSerpentisCommanderBattleCruiser.yaml";
        private Map<String, AsteroidSerpentisCommanderBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisCommanderBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  811;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisCommanderBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisCommanderBattleCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSerpentisCommanderBattleCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisCommanderBattleCruiser> types;
        }
    }
}
