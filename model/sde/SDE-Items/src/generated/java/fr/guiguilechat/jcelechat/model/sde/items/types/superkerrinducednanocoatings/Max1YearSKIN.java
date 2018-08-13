package fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class Max1YearSKIN
    extends SuperKerrInducedNanocoatings
{
    public final static Max1YearSKIN.MetaGroup METAGROUP = new Max1YearSKIN.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/superkerrinducednanocoatings/Max1YearSKIN.yaml";
    private static Map<String, Max1YearSKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1955;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Max1YearSKIN> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Max1YearSKIN> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Max1YearSKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Max1YearSKIN> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Max1YearSKIN>
    {

        @Override
        public MetaCategory<? super Max1YearSKIN> category() {
            return SuperKerrInducedNanocoatings.METACAT;
        }

        @Override
        public String getName() {
            return "Max1YearSKIN";
        }

        @Override
        public Collection<Max1YearSKIN> items() {
            return (load().values());
        }
    }
}
