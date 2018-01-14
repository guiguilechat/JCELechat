
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class PlanetaryCloud
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/celestial/PlanetaryCloud.yaml";
    private static LinkedHashMap<String, PlanetaryCloud> cache = (null);

    @Override
    public int getGroupId() {
        return  312;
    }

    @Override
    public Class<?> getGroup() {
        return PlanetaryCloud.class;
    }

    public static LinkedHashMap<String, PlanetaryCloud> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PlanetaryCloud> items;

    }

}
