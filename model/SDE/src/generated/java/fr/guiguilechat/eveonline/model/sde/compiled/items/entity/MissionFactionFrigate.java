
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionFactionFrigate.yaml";
    private static LinkedHashMap<String, MissionFactionFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1007;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionFrigate.class;
    }

    public static LinkedHashMap<String, MissionFactionFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionFactionFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionFactionFrigate> items;

    }

}
