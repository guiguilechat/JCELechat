
package fr.guiguilechat.eveonline.model.sde.compiled.items.ancientrelics;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperOffensiveRelics
    extends AncientRelics
{

    public final static String RESOURCE_PATH = "SDE/ancientrelics/SleeperOffensiveRelics.yaml";
    private static LinkedHashMap<String, SleeperOffensiveRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  991;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperOffensiveRelics.class;
    }

    public static LinkedHashMap<String, SleeperOffensiveRelics> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SleeperOffensiveRelics> items;

    }

}
