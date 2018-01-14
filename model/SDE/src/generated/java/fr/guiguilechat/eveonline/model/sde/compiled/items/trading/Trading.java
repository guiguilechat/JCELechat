
package fr.guiguilechat.eveonline.model.sde.compiled.items.trading;

import java.io.FileReader;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;

public class Trading
    extends fr.guiguilechat.eveonline.model.sde.compiled.items.Trading
{

    public final static String RESOURCE_PATH = "SDE/trading/Trading.yaml";
    private static LinkedHashMap<String, Trading> cache = (null);

    @Override
    public int getGroupId() {
        return  94;
    }

    @Override
    public Class<?> getGroup() {
        return Trading.class;
    }

    public static LinkedHashMap<String, Trading> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Trading> items;

    }

}
