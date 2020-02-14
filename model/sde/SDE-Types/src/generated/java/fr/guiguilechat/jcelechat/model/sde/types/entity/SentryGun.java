package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SentryGun
    extends Entity
{
    public static final SentryGun.MetaGroup METAGROUP = new SentryGun.MetaGroup();

    @Override
    public IMetaGroup<SentryGun> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SentryGun>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/SentryGun.yaml";
        private Map<String, SentryGun> cache = (null);

        @Override
        public IMetaCategory<? super SentryGun> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  99;
        }

        @Override
        public String getName() {
            return "SentryGun";
        }

        @Override
        public synchronized Map<String, SentryGun> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SentryGun.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SentryGun> types;
        }
    }
}
