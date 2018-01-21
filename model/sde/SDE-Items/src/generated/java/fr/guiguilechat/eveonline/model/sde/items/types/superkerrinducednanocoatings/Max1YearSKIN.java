
package fr.guiguilechat.eveonline.model.sde.items.types.superkerrinducednanocoatings;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class Max1YearSKIN
    extends SuperKerrInducedNanocoatings
{

    public final static String RESOURCE_PATH = "SDE/items/superkerrinducednanocoatings/Max1YearSKIN.yaml";
    private static LinkedHashMap<String, Max1YearSKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1955;
    }

    @Override
    public Class<?> getGroup() {
        return Max1YearSKIN.class;
    }

    public static LinkedHashMap<String, Max1YearSKIN> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Max1YearSKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Max1YearSKIN> items;

    }

}
