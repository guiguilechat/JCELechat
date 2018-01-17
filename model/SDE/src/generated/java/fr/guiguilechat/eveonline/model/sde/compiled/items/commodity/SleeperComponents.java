
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class SleeperComponents
    extends Commodity
{

    public final static String RESOURCE_PATH = "SDE/items/commodity/SleeperComponents.yaml";
    private static LinkedHashMap<String, SleeperComponents> cache = (null);

    @Override
    public int getGroupId() {
        return  880;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperComponents.class;
    }

    public static LinkedHashMap<String, SleeperComponents> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SleeperComponents> items;

    }

}
