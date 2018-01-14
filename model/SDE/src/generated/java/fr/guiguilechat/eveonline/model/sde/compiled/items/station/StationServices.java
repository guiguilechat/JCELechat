
package fr.guiguilechat.eveonline.model.sde.compiled.items.station;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Station;
import org.yaml.snakeyaml.Yaml;

public class StationServices
    extends Station
{

    public final static String RESOURCE_PATH = "SDE/station/StationServices.yaml";
    private static LinkedHashMap<String, StationServices> cache = (null);

    @Override
    public int getGroupId() {
        return  16;
    }

    @Override
    public Class<?> getGroup() {
        return StationServices.class;
    }

    public static LinkedHashMap<String, StationServices> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StationServices> items;

    }

}
