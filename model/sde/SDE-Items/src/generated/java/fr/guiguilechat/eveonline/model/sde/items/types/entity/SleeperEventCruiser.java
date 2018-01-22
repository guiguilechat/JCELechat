package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SleeperEventCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/SleeperEventCruiser.yaml";
    private static LinkedHashMap<String, SleeperEventCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1927;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperEventCruiser.class;
    }

    public static LinkedHashMap<String, SleeperEventCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperEventCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperEventCruiser> items;
    }
}
