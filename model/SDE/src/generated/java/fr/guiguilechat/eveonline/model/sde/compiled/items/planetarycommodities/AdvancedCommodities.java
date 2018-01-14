
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetarycommodities;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class AdvancedCommodities
    extends PlanetaryCommodities
{

    public final static String RESOURCE_PATH = "SDE/planetarycommodities/AdvancedCommodities.yaml";
    private static LinkedHashMap<String, AdvancedCommodities> cache = (null);

    @Override
    public int getGroupId() {
        return  1041;
    }

    @Override
    public Class<?> getGroup() {
        return AdvancedCommodities.class;
    }

    public static LinkedHashMap<String, AdvancedCommodities> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AdvancedCommodities> items;

    }

}
