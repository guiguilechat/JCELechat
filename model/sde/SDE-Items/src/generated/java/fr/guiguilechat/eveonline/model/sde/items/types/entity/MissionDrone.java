package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionDrone
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionDrone.yaml";
    private static LinkedHashMap<String, MissionDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  337;
    }

    @Override
    public Class<?> getGroup() {
        return MissionDrone.class;
    }

    public static LinkedHashMap<String, MissionDrone> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionDrone> items;
    }
}
