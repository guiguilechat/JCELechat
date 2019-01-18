package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneSupercarrier
    extends Entity
{
    public static final AsteroidRogueDroneSupercarrier.MetaGroup METAGROUP = new AsteroidRogueDroneSupercarrier.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneSupercarrier> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneSupercarrier>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneSupercarrier.yaml";
        private Map<String, AsteroidRogueDroneSupercarrier> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneSupercarrier> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1692;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneSupercarrier";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneSupercarrier> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneSupercarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneSupercarrier> items;
        }
    }
}
