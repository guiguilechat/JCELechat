package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DestructibleSentryGun
    extends Entity
{
    public final static DestructibleSentryGun.MetaGroup METAGROUP = new DestructibleSentryGun.MetaGroup();

    @Override
    public IMetaGroup<DestructibleSentryGun> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DestructibleSentryGun>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DestructibleSentryGun.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DestructibleSentryGun.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DestructibleSentryGun> items;
        }
    }
}
