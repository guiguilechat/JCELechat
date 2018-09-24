package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisBattleCruiser
    extends Entity
{
    public final static AsteroidSerpentisBattleCruiser.MetaGroup METAGROUP = new AsteroidSerpentisBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisBattleCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisBattleCruiser.yaml";
        private Map<String, AsteroidSerpentisBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSerpentisBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  584;
        }

        @Override
        public String getName() {
            return "AsteroidSerpentisBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidSerpentisBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisBattleCruiser> items;
        }
    }
}
