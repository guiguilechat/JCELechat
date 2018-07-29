package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class StorylineBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/StorylineBattleship.yaml";
    private static LinkedHashMap<String, StorylineBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  523;
    }

    @Override
    public Class<?> getGroup() {
        return StorylineBattleship.class;
    }

    public static synchronized LinkedHashMap<String, StorylineBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StorylineBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StorylineBattleship> items;
    }
}
