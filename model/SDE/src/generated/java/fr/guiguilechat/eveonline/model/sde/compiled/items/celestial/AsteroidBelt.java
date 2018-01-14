
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBelt
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/celestial/AsteroidBelt.yaml";
    private static LinkedHashMap<String, AsteroidBelt> cache = (null);

    @Override
    public int getGroupId() {
        return  9;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBelt.class;
    }

    public static LinkedHashMap<String, AsteroidBelt> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidBelt> items;

    }

}
