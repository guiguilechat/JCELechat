package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StorylineCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/StorylineCruiser.yaml";
    private static LinkedHashMap<String, StorylineCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  522;
    }

    @Override
    public Class<?> getGroup() {
        return StorylineCruiser.class;
    }

    public static LinkedHashMap<String, StorylineCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StorylineCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StorylineCruiser> items;
    }
}
