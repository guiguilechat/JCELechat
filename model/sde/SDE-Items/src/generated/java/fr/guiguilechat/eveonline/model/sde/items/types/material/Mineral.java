
package fr.guiguilechat.eveonline.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class Mineral
    extends Material
{

    public final static String RESOURCE_PATH = "SDE/items/material/Mineral.yaml";
    private static LinkedHashMap<String, Mineral> cache = (null);

    @Override
    public int getGroupId() {
        return  18;
    }

    @Override
    public Class<?> getGroup() {
        return Mineral.class;
    }

    public static LinkedHashMap<String, Mineral> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Mineral.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Mineral> items;

    }

}
