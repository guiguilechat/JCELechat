package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisBattleCruiser
    extends Entity
{
    public static final AsteroidSerpentisBattleCruiser.MetaGroup METAGROUP = new AsteroidSerpentisBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSerpentisBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSerpentisBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSerpentisBattleCruiser.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSerpentisBattleCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSerpentisBattleCruiser> types;
        }
    }
}
