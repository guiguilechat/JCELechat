
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class SuperWeaponBeacon
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/celestial/SuperWeaponBeacon.yaml";
    private static LinkedHashMap<String, SuperWeaponBeacon> cache = (null);

    @Override
    public int getGroupId() {
        return  1704;
    }

    @Override
    public Class<?> getGroup() {
        return SuperWeaponBeacon.class;
    }

    public static LinkedHashMap<String, SuperWeaponBeacon> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SuperWeaponBeacon> items;

    }

}
