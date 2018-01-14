
package fr.guiguilechat.eveonline.model.sde.compiled.items.placeables;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Placeables;
import org.yaml.snakeyaml.Yaml;

public class Furniture
    extends Placeables
{

    public final static String RESOURCE_PATH = "SDE/placeables/Furniture.yaml";
    private static LinkedHashMap<String, Furniture> cache = (null);

    @Override
    public int getGroupId() {
        return  940;
    }

    @Override
    public Class<?> getGroup() {
        return Furniture.class;
    }

    public static LinkedHashMap<String, Furniture> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Furniture> items;

    }

}
