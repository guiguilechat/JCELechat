
package fr.guiguilechat.eveonline.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperEngineeringRelics
    extends AncientRelics
{

    public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperEngineeringRelics.yaml";
    private static LinkedHashMap<String, SleeperEngineeringRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  992;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperEngineeringRelics.class;
    }

    public static LinkedHashMap<String, SleeperEngineeringRelics> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperEngineeringRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SleeperEngineeringRelics> items;

    }

}
