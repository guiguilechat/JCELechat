
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionKhanidFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionKhanidFrigate.yaml";
    private static LinkedHashMap<String, MissionKhanidFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  687;
    }

    @Override
    public Class<?> getGroup() {
        return MissionKhanidFrigate.class;
    }

    public static LinkedHashMap<String, MissionKhanidFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionKhanidFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionKhanidFrigate> items;

    }

}
