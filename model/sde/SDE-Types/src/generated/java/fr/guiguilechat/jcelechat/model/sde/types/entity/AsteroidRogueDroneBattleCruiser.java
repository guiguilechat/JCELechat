package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneBattleCruiser
    extends Entity
{
    public static final AsteroidRogueDroneBattleCruiser.MetaGroup METAGROUP = new AsteroidRogueDroneBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidRogueDroneBattleCruiser.yaml";
        private Map<String, AsteroidRogueDroneBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  755;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneBattleCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidRogueDroneBattleCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneBattleCruiser> types;
        }
    }
}
