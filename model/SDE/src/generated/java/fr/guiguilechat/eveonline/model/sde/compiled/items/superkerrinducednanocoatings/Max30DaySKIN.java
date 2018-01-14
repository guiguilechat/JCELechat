
package fr.guiguilechat.eveonline.model.sde.compiled.items.superkerrinducednanocoatings;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class Max30DaySKIN
    extends SuperKerrInducedNanocoatings
{

    public final static String RESOURCE_PATH = "SDE/superkerrinducednanocoatings/Max30DaySKIN.yaml";
    private static LinkedHashMap<String, Max30DaySKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1953;
    }

    @Override
    public Class<?> getGroup() {
        return Max30DaySKIN.class;
    }

    public static LinkedHashMap<String, Max30DaySKIN> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Max30DaySKIN> items;

    }

}
