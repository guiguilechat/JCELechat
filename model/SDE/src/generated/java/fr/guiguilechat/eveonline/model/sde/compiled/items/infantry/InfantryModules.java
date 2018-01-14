
package fr.guiguilechat.eveonline.model.sde.compiled.items.infantry;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryModules
    extends Infantry
{

    public final static String RESOURCE_PATH = "SDE/infantry/InfantryModules.yaml";
    private static LinkedHashMap<String, InfantryModules> cache = (null);

    @Override
    public int getGroupId() {
        return  351121;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryModules.class;
    }

    public static LinkedHashMap<String, InfantryModules> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, InfantryModules> items;

    }

}
