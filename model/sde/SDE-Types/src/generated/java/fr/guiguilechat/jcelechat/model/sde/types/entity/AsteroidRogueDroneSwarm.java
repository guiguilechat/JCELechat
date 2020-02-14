package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneSwarm
    extends Entity
{
    public static final AsteroidRogueDroneSwarm.MetaGroup METAGROUP = new AsteroidRogueDroneSwarm.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneSwarm> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneSwarm>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidRogueDroneSwarm.yaml";
        private Map<String, AsteroidRogueDroneSwarm> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneSwarm> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  761;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneSwarm";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneSwarm> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidRogueDroneSwarm.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneSwarm> types;
        }
    }
}
