package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGenericDestroyers
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionGenericDestroyers.yaml";
    private static LinkedHashMap<String, MissionGenericDestroyers> cache = (null);

    @Override
    public int getGroupId() {
        return  829;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericDestroyers.class;
    }

    public static LinkedHashMap<String, MissionGenericDestroyers> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionGenericDestroyers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionGenericDestroyers> items;
    }
}
