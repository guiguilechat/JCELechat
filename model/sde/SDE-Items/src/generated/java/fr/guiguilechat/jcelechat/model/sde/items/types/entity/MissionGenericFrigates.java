package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionGenericFrigates
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionGenericFrigates.yaml";
    private static LinkedHashMap<String, MissionGenericFrigates> cache = (null);

    @Override
    public int getGroupId() {
        return  818;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericFrigates.class;
    }

    public static synchronized LinkedHashMap<String, MissionGenericFrigates> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionGenericFrigates.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionGenericFrigates> items;
    }
}
