
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class ForceField
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/celestial/ForceField.yaml";
    private static LinkedHashMap<String, ForceField> cache = (null);

    @Override
    public int getGroupId() {
        return  411;
    }

    @Override
    public Class<?> getGroup() {
        return ForceField.class;
    }

    public static LinkedHashMap<String, ForceField> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ForceField> items;

    }

}
