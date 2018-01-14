
package fr.guiguilechat.eveonline.model.sde.compiled.items.ancientrelics;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperDefensiveRelics
    extends AncientRelics
{

    public final static String RESOURCE_PATH = "SDE/ancientrelics/SleeperDefensiveRelics.yaml";
    private static LinkedHashMap<String, SleeperDefensiveRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  993;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperDefensiveRelics.class;
    }

    public static LinkedHashMap<String, SleeperDefensiveRelics> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SleeperDefensiveRelics> items;

    }

}
