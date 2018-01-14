
package fr.guiguilechat.eveonline.model.sde.compiled.items.placeables;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Placeables;
import org.yaml.snakeyaml.Yaml;

public class Generic
    extends Placeables
{

    public final static String RESOURCE_PATH = "SDE/placeables/Generic.yaml";
    private static LinkedHashMap<String, Generic> cache = (null);

    @Override
    public int getGroupId() {
        return  1079;
    }

    @Override
    public Class<?> getGroup() {
        return Generic.class;
    }

    public static LinkedHashMap<String, Generic> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Generic> items;

    }

}
