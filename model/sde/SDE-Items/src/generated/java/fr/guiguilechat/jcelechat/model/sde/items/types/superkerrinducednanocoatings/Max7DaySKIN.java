package fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class Max7DaySKIN
    extends SuperKerrInducedNanocoatings
{
    public final static Max7DaySKIN.MetaGroup METAGROUP = new Max7DaySKIN.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/superkerrinducednanocoatings/Max7DaySKIN.yaml";
    private static Map<String, Max7DaySKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1952;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Max7DaySKIN> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Max7DaySKIN> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Max7DaySKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Max7DaySKIN> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Max7DaySKIN>
    {

        @Override
        public MetaCategory<? super Max7DaySKIN> category() {
            return SuperKerrInducedNanocoatings.METACAT;
        }

        @Override
        public String getName() {
            return "Max7DaySKIN";
        }

        @Override
        public Collection<Max7DaySKIN> items() {
            return (load().values());
        }
    }
}
