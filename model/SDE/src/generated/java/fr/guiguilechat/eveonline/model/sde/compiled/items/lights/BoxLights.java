
package fr.guiguilechat.eveonline.model.sde.compiled.items.lights;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Lights;
import org.yaml.snakeyaml.Yaml;

public class BoxLights
    extends Lights
{

    public final static String RESOURCE_PATH = "SDE/lights/BoxLights.yaml";
    private static LinkedHashMap<String, BoxLights> cache = (null);

    @Override
    public int getGroupId() {
        return  1111;
    }

    @Override
    public Class<?> getGroup() {
        return BoxLights.class;
    }

    public static LinkedHashMap<String, BoxLights> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, BoxLights> items;

    }

}
