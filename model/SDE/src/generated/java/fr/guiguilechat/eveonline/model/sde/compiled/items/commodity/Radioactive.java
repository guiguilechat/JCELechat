
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Radioactive
    extends Commodity
{

    public final static String RESOURCE_PATH = "SDE/commodity/Radioactive.yaml";
    private static LinkedHashMap<String, Radioactive> cache = (null);

    @Override
    public int getGroupId() {
        return  282;
    }

    @Override
    public Class<?> getGroup() {
        return Radioactive.class;
    }

    public static LinkedHashMap<String, Radioactive> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Radioactive> items;

    }

}
