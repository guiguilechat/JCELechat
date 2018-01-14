
package fr.guiguilechat.eveonline.model.sde.compiled.items.abstrct;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class Miscellaneous
    extends Abstrct
{

    public final static String RESOURCE_PATH = "SDE/abstrct/Miscellaneous.yaml";
    private static LinkedHashMap<String, Miscellaneous> cache = (null);

    @Override
    public int getGroupId() {
        return  1319;
    }

    @Override
    public Class<?> getGroup() {
        return Miscellaneous.class;
    }

    public static LinkedHashMap<String, Miscellaneous> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Miscellaneous> items;

    }

}
