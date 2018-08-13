package fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class Max30DaySKIN
    extends SuperKerrInducedNanocoatings
{
    public final static Max30DaySKIN.MetaGroup METAGROUP = new Max30DaySKIN.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/superkerrinducednanocoatings/Max30DaySKIN.yaml";
    private static Map<String, Max30DaySKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1953;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Max30DaySKIN> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Max30DaySKIN> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Max30DaySKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Max30DaySKIN> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Max30DaySKIN>
    {

        @Override
        public MetaCategory<? super Max30DaySKIN> category() {
            return SuperKerrInducedNanocoatings.METACAT;
        }

        @Override
        public String getName() {
            return "Max30DaySKIN";
        }

        @Override
        public Collection<Max30DaySKIN> items() {
            return (load().values());
        }
    }
}
