package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class TemporaryCloud
    extends Entity
{
    public static final TemporaryCloud.MetaGroup METAGROUP = new TemporaryCloud.MetaGroup();

    @Override
    public IMetaGroup<TemporaryCloud> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TemporaryCloud>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/TemporaryCloud.yaml";
        private Map<String, TemporaryCloud> cache = (null);

        @Override
        public IMetaCategory<? super TemporaryCloud> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  335;
        }

        @Override
        public String getName() {
            return "TemporaryCloud";
        }

        @Override
        public synchronized Map<String, TemporaryCloud> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(TemporaryCloud.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TemporaryCloud> types;
        }
    }
}
