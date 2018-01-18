
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

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

    public static LinkedHashMap<String, StorylineFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StorylineFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StorylineFrigate> items;

    }

}
