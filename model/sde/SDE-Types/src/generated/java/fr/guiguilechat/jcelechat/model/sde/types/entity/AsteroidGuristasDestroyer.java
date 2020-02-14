package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasDestroyer
    extends Entity
{
    public static final AsteroidGuristasDestroyer.MetaGroup METAGROUP = new AsteroidGuristasDestroyer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidGuristasDestroyer.yaml";
        private Map<String, AsteroidGuristasDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  579;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasDestroyer";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasDestroyer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidGuristasDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasDestroyer> types;
        }
    }
}
