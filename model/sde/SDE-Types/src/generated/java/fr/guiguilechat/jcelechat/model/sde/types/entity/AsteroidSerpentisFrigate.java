package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisFrigate
    extends Entity
{
    public static final AsteroidSerpentisFrigate.MetaGroup METAGROUP = new AsteroidSerpentisFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisFrigate.yaml";
        private Map<String, AsteroidSerpentisFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  572;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisFrigate> items;
        }
    }
}
