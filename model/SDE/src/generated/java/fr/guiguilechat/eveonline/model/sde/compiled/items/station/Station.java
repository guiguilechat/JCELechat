
package fr.guiguilechat.eveonline.model.sde.compiled.items.station;

import java.io.FileReader;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;

public class Station
    extends fr.guiguilechat.eveonline.model.sde.compiled.items.Station
{

    public final static String RESOURCE_PATH = "SDE/station/Station.yaml";
    private static LinkedHashMap<String, Station> cache = (null);

    @Override
    public int getGroupId() {
        return  15;
    }

    @Override
    public Class<?> getGroup() {
        return Station.class;
    }

    public static LinkedHashMap<String, Station> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Station> items;

    }

}
