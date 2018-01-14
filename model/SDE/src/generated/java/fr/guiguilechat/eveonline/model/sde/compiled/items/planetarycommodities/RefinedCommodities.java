
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetarycommodities;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class RefinedCommodities
    extends PlanetaryCommodities
{

    public final static String RESOURCE_PATH = "SDE/planetarycommodities/RefinedCommodities.yaml";
    private static LinkedHashMap<String, RefinedCommodities> cache = (null);

    @Override
    public int getGroupId() {
        return  1034;
    }

    @Override
    public Class<?> getGroup() {
        return RefinedCommodities.class;
    }

    public static LinkedHashMap<String, RefinedCommodities> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, RefinedCommodities> items;

    }

}
