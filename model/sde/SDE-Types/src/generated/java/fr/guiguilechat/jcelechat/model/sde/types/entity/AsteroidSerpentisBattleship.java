package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisBattleship
    extends Entity
{
    public static final AsteroidSerpentisBattleship.MetaGroup METAGROUP = new AsteroidSerpentisBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisBattleship.yaml";
        private Map<String, AsteroidSerpentisBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  570;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisBattleship> items;
        }
    }
}
