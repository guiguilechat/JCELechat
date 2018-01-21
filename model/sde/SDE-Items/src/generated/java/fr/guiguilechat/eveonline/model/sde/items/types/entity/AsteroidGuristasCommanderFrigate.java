
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCommanderFrigate.yaml";
    private static LinkedHashMap<String, AsteroidGuristasCommanderFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  800;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasCommanderFrigate.class;
    }

    public static LinkedHashMap<String, AsteroidGuristasCommanderFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidGuristasCommanderFrigate> items;

    }

}
