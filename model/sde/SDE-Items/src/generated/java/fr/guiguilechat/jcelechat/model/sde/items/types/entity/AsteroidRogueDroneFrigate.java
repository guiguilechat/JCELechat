package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneFrigate
    extends Entity
{
    public final static AsteroidRogueDroneFrigate.MetaGroup METAGROUP = new AsteroidRogueDroneFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneFrigate.yaml";
        private Map<String, AsteroidRogueDroneFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  759;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneFrigate> items;
        }
    }
}