package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StorylineMissionCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/StorylineMissionCruiser.yaml";
    private static LinkedHashMap<String, StorylineMissionCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  533;
    }

    @Override
    public Class<?> getGroup() {
        return StorylineMissionCruiser.class;
    }

    public static synchronized LinkedHashMap<String, StorylineMissionCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StorylineMissionCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StorylineMissionCruiser> items;
    }
}
