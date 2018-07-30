package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicCruiser.yaml";
    private static LinkedHashMap<String, MissionMinmatarRepublicCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  705;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMinmatarRepublicCruiser.class;
    }

    public static synchronized LinkedHashMap<String, MissionMinmatarRepublicCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionMinmatarRepublicCruiser> items;
    }
}
