
package fr.guiguilechat.eveonline.model.sde.compiled.items.trading;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;

public class Trading
    extends fr.guiguilechat.eveonline.model.sde.compiled.items.Trading
{

    public final static String RESOURCE_PATH = "SDE/items/trading/Trading.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(Trading.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Trading> items;

    }

}
