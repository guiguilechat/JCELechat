package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class StorylineFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/StorylineFrigate.yaml";
    private static LinkedHashMap<String, StorylineFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  520;
    }

    @Override
    public Class<?> getGroup() {
        return StorylineFrigate.class;
    }

    public static synchronized LinkedHashMap<String, StorylineFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StorylineFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StorylineFrigate> items;
    }
}
