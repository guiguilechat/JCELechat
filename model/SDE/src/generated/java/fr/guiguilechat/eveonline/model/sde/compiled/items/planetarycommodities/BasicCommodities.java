
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetarycommodities;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class BasicCommodities
    extends PlanetaryCommodities
{

    public final static String RESOURCE_PATH = "SDE/planetarycommodities/BasicCommodities.yaml";
    private static LinkedHashMap<String, BasicCommodities> cache = (null);

    @Override
    public int getGroupId() {
        return  1042;
    }

    @Override
    public Class<?> getGroup() {
        return BasicCommodities.class;
    }

    public static LinkedHashMap<String, BasicCommodities> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, BasicCommodities> items;

    }

}
