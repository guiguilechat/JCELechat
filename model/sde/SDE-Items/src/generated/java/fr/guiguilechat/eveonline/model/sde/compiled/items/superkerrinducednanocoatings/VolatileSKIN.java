
package fr.guiguilechat.eveonline.model.sde.compiled.items.superkerrinducednanocoatings;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.SuperKerrInducedNanocoatings;
import org.yaml.snakeyaml.Yaml;

public class VolatileSKIN
    extends SuperKerrInducedNanocoatings
{

    public final static String RESOURCE_PATH = "SDE/items/superkerrinducednanocoatings/VolatileSKIN.yaml";
    private static LinkedHashMap<String, VolatileSKIN> cache = (null);

    @Override
    public int getGroupId() {
        return  1951;
    }

    @Override
    public Class<?> getGroup() {
        return VolatileSKIN.class;
    }

    public static LinkedHashMap<String, VolatileSKIN> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(VolatileSKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, VolatileSKIN> items;

    }

}
