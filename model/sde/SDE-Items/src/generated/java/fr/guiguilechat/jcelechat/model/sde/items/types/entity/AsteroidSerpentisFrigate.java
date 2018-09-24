package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisFrigate
    extends Entity
{
    public final static AsteroidSerpentisFrigate.MetaGroup METAGROUP = new AsteroidSerpentisFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisFrigate.yaml";
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
