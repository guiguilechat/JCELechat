
package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class MoonMiningBeacon
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/items/celestial/MoonMiningBeacon.yaml";
    private static LinkedHashMap<String, MoonMiningBeacon> cache = (null);

    @Override
    public int getGroupId() {
        return  1915;
    }

    @Override
    public Class<?> getGroup() {
        return MoonMiningBeacon.class;
    }

    public static LinkedHashMap<String, MoonMiningBeacon> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MoonMiningBeacon.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MoonMiningBeacon> items;

    }

}
