package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCruiser
    extends Entity
{
    public static final AsteroidRogueDroneCruiser.MetaGroup METAGROUP = new AsteroidRogueDroneCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidRogueDroneCruiser.yaml";
        private Map<String, AsteroidRogueDroneCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  757;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidRogueDroneCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneCruiser> types;
        }
    }
}
