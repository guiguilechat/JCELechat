package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class GuristasEventCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/GuristasEventCruiser.yaml";
    private static LinkedHashMap<String, GuristasEventCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1665;
    }

    @Override
    public Class<?> getGroup() {
        return GuristasEventCruiser.class;
    }

    public static LinkedHashMap<String, GuristasEventCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GuristasEventCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, GuristasEventCruiser> items;
    }
}
