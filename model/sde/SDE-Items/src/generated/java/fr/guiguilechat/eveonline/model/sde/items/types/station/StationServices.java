
package fr.guiguilechat.eveonline.model.sde.items.types.station;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Station;
import org.yaml.snakeyaml.Yaml;

public class StationServices
    extends Station
{

    public final static String RESOURCE_PATH = "SDE/items/station/StationServices.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(StationServices.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StationServices> items;

    }

}
