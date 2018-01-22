package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class GuristasEventFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/GuristasEventFrigate.yaml";
    private static LinkedHashMap<String, GuristasEventFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1664;
    }

    @Override
    public Class<?> getGroup() {
        return GuristasEventFrigate.class;
    }

    public static LinkedHashMap<String, GuristasEventFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GuristasEventFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, GuristasEventFrigate> items;
    }
}
