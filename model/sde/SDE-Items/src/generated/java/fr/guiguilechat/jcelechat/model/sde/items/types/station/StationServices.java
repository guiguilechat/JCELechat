package fr.guiguilechat.jcelechat.model.sde.items.types.station;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Station;

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

    public static synchronized LinkedHashMap<String, StationServices> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StationServices.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StationServices> items;
    }
}
