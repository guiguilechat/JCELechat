
package fr.guiguilechat.eveonline.model.sde.compiled.items.material;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Material;
import org.yaml.snakeyaml.Yaml;

public class Mineral
    extends Material
{

    public final static String RESOURCE_PATH = "SDE/material/Mineral.yaml";
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
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Mineral> items;

    }

}
