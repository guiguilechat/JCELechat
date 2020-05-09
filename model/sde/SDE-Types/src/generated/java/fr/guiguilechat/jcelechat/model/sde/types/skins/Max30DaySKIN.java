package fr.guiguilechat.jcelechat.model.sde.types.skins;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.SKINs;
import org.yaml.snakeyaml.Yaml;

public class Max30DaySKIN
    extends SKINs
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Max30DaySKIN.MetaGroup METAGROUP = new Max30DaySKIN.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Max30DaySKIN> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Max30DaySKIN>
    {
        public static final String RESOURCE_PATH = "SDE/types/skins/Max30DaySKIN.yaml";
        private Map<String, Max30DaySKIN> cache = (null);

        @Override
        public IMetaCategory<? super Max30DaySKIN> category() {
            return SKINs.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1953;
        }

        @Override
        public String getName() {
            return "Max30DaySKIN";
        }

        @Override
        public synchronized Map<String, Max30DaySKIN> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Max30DaySKIN.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Max30DaySKIN> types;
        }
    }
}
