
package fr.guiguilechat.eveonline.model.sde.compiled.items.superkerrinducednanocoatings;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class Max90DaySKIN
    extends SuperKerrInducedNanocoatings
{

    public final static String RESOURCE_PATH = "SDE/superkerrinducednanocoatings/Max90DaySKIN.yaml";
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
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Max90DaySKIN> items;

    }

}
