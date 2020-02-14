package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DestructibleSentryGun
    extends Entity
{
    public static final DestructibleSentryGun.MetaGroup METAGROUP = new DestructibleSentryGun.MetaGroup();

    @Override
    public IMetaGroup<DestructibleSentryGun> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DestructibleSentryGun>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DestructibleSentryGun.yaml";
        private Map<String, DestructibleSentryGun> cache = (null);

        @Override
        public IMetaCategory<? super DestructibleSentryGun> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  383;
        }

        @Override
        public String getName() {
            return "DestructibleSentryGun";
        }

        @Override
        public synchronized Map<String, DestructibleSentryGun> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DestructibleSentryGun.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DestructibleSentryGun> types;
        }
    }
}
