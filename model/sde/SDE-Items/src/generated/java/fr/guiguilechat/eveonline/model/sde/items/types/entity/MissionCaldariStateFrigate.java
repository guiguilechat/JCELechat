
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateFrigate.yaml";
    private static LinkedHashMap<String, MissionCaldariStateFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  671;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCaldariStateFrigate.class;
    }

    public static LinkedHashMap<String, MissionCaldariStateFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionCaldariStateFrigate> items;

    }

}
