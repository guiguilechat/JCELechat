
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class AccelerationGateKeys
    extends Commodity
{

    public final static String RESOURCE_PATH = "SDE/commodity/AccelerationGateKeys.yaml";
    private static LinkedHashMap<String, AccelerationGateKeys> cache = (null);

    @Override
    public int getGroupId() {
        return  474;
    }

    @Override
    public Class<?> getGroup() {
        return AccelerationGateKeys.class;
    }

    public static LinkedHashMap<String, AccelerationGateKeys> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AccelerationGateKeys> items;

    }

}
