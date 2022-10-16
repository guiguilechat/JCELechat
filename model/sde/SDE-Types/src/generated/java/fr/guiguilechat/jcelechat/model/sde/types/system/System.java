package fr.guiguilechat.jcelechat.model.sde.types.system;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import org.yaml.snakeyaml.Yaml;

public class System
    extends fr.guiguilechat.jcelechat.model.sde.types.System
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final System.MetaGroup METAGROUP = new System.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<System> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<System>
    {
        public static final String RESOURCE_PATH = "SDE/types/system/System.yaml";
        private Map<String, System> cache = (null);

        @Override
        public IMetaCategory<? super System> category() {
            return fr.guiguilechat.jcelechat.model.sde.types.System.METACAT;
        }

        @Override
        public int getGroupId() {
            return  0;
        }

        @Override
        public String getName() {
            return "System";
        }

        @Override
        public synchronized Map<String, System> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(System.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, System> types;
        }
    }
}
