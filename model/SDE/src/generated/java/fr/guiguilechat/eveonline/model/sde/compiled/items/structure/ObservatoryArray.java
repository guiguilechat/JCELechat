
package fr.guiguilechat.eveonline.model.sde.compiled.items.structure;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Structure;
import org.yaml.snakeyaml.Yaml;

public class ObservatoryArray
    extends Structure
{

    public final static String RESOURCE_PATH = "SDE/structure/ObservatoryArray.yaml";
    private static LinkedHashMap<String, ObservatoryArray> cache = (null);

    @Override
    public int getGroupId() {
        return  1407;
    }

    @Override
    public Class<?> getGroup() {
        return ObservatoryArray.class;
    }

    public static LinkedHashMap<String, ObservatoryArray> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ObservatoryArray> items;

    }

}
