package fr.guiguilechat.jcelechat.model.sde.items.types.superkerrinducednanocoatings;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.SuperKerrInducedNanocoatings;

public class Max7DaySKIN
    extends SuperKerrInducedNanocoatings
{
    public final static String RESOURCE_PATH = "SDE/items/superkerrinducednanocoatings/Max7DaySKIN.yaml";
    private static LinkedHashMap<String, Max7DaySKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1952;
    }

    @Override
    public Class<?> getGroup() {
        return Max7DaySKIN.class;
    }

    public static synchronized LinkedHashMap<String, Max7DaySKIN> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Max7DaySKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Max7DaySKIN> items;
    }
}
