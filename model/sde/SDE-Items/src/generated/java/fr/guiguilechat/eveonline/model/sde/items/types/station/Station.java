package fr.guiguilechat.eveonline.model.sde.items.types.station;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;

public class Station
    extends fr.guiguilechat.eveonline.model.sde.items.types.Station
{
    public final static String RESOURCE_PATH = "SDE/items/station/Station.yaml";
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
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Station.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Station> items;
    }
}
