
package fr.guiguilechat.eveonline.model.sde.items.types.lights;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Lights;
import org.yaml.snakeyaml.Yaml;

public class SpotLights
    extends Lights
{

    public final static String RESOURCE_PATH = "SDE/items/lights/SpotLights.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(SpotLights.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SpotLights> items;

    }

}
