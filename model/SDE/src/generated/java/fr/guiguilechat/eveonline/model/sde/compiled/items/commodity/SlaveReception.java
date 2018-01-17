
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class SlaveReception
    extends Commodity
{

    public final static String RESOURCE_PATH = "SDE/items/commodity/SlaveReception.yaml";
    private static LinkedHashMap<String, SlaveReception> cache = (null);

    @Override
    public int getGroupId() {
        return  879;
    }

    @Override
    public Class<?> getGroup() {
        return SlaveReception.class;
    }

    public static LinkedHashMap<String, SlaveReception> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SlaveReception.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SlaveReception> items;

    }

}
