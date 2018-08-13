package fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class PermanentSKIN
    extends SuperKerrInducedNanocoatings
{
    public final static PermanentSKIN.MetaGroup METAGROUP = new PermanentSKIN.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/superkerrinducednanocoatings/PermanentSKIN.yaml";
    private static Map<String, PermanentSKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1950;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PermanentSKIN> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, PermanentSKIN> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PermanentSKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, PermanentSKIN> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<PermanentSKIN>
    {

        @Override
        public MetaCategory<? super PermanentSKIN> category() {
            return SuperKerrInducedNanocoatings.METACAT;
        }

        @Override
        public String getName() {
            return "PermanentSKIN";
        }

        @Override
        public Collection<PermanentSKIN> items() {
            return (load().values());
        }
    }
}
