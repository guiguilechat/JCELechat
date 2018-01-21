
package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class ForceField
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/items/celestial/ForceField.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(ForceField.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ForceField> items;

    }

}
