package fr.guiguilechat.jcelechat.model.sde.types.owner;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Owner;
import org.yaml.snakeyaml.Yaml;

public class Alliance
    extends Owner
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Alliance.MetaGroup METAGROUP = new Alliance.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Alliance> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Alliance>
    {
        public static final String RESOURCE_PATH = "SDE/types/owner/Alliance.yaml";
        private Map<String, Alliance> cache = (null);

        @Override
        public IMetaCategory<? super Alliance> category() {
            return Owner.METACAT;
        }

        @Override
        public int getGroupId() {
            return  32;
        }

        @Override
        public String getName() {
            return "Alliance";
        }

        @Override
        public synchronized Map<String, Alliance> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Alliance.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Alliance> types;
        }
    }
}
