package fr.guiguilechat.eveonline.model.sde.items.types.superkerrinducednanocoatings;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class Max90DaySKIN
    extends SuperKerrInducedNanocoatings
{
    public final static String RESOURCE_PATH = "SDE/items/superkerrinducednanocoatings/Max90DaySKIN.yaml";
    private static LinkedHashMap<String, Max90DaySKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1954;
    }

    @Override
    public Class<?> getGroup() {
        return Max90DaySKIN.class;
    }

    public static LinkedHashMap<String, Max90DaySKIN> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Max90DaySKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Max90DaySKIN> items;
    }
}
