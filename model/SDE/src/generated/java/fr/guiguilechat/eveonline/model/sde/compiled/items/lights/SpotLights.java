
package fr.guiguilechat.eveonline.model.sde.compiled.items.lights;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Lights;
import org.yaml.snakeyaml.Yaml;

public class SpotLights
    extends Lights
{

    public final static String RESOURCE_PATH = "SDE/lights/SpotLights.yaml";
    private static LinkedHashMap<String, SpotLights> cache = (null);

    @Override
    public int getGroupId() {
        return  1112;
    }

    @Override
    public Class<?> getGroup() {
        return SpotLights.class;
    }

    public static LinkedHashMap<String, SpotLights> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SpotLights> items;

    }

}