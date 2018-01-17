
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

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

    public static LinkedHashMap<String, MissionGenericFrigates> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionGenericFrigates.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionGenericFrigates> items;

    }

}
