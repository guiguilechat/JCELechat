
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class StorylineCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/StorylineCruiser.yaml";
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
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StorylineCruiser> items;

    }

}
