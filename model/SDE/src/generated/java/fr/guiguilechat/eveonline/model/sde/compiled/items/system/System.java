
package fr.guiguilechat.eveonline.model.sde.compiled.items.system;

import java.io.FileReader;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;

public class System
    extends fr.guiguilechat.eveonline.model.sde.compiled.items.System
{

    public final static String RESOURCE_PATH = "SDE/system/System.yaml";
    private static LinkedHashMap<String, System> cache = (null);

    @Override
    public int getGroupId() {
        return  0;
    }

    @Override
    public Class<?> getGroup() {
        return System.class;
    }

    public static LinkedHashMap<String, System> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, System> items;

    }

}
