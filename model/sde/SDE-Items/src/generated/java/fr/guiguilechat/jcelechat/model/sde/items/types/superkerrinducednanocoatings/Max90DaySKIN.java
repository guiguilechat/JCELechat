package fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class Max90DaySKIN
    extends SuperKerrInducedNanocoatings
{
    public final static String RESOURCE_PATH = "SDE/items/superkerrinducednanocoatings/Max90DaySKIN.yaml";
    private static Map<String, Max90DaySKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1954;
    }

    @Override
    public Class<?> getGroup() {
        return Max90DaySKIN.class;
    }

    public static synchronized Map<String, Max90DaySKIN> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Max90DaySKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Max90DaySKIN> items;
    }
}
