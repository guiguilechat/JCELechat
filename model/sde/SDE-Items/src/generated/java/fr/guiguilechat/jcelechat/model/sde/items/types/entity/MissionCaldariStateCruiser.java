package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateCruiser.yaml";
    private static LinkedHashMap<String, MissionCaldariStateCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  673;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCaldariStateCruiser.class;
    }

    public static synchronized LinkedHashMap<String, MissionCaldariStateCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionCaldariStateCruiser> items;
    }
}
