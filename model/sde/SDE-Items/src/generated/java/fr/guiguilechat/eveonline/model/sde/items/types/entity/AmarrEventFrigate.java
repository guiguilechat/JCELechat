
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrEventFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AmarrEventFrigate.yaml";
    private static LinkedHashMap<String, AmarrEventFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1759;
    }

    @Override
    public Class<?> getGroup() {
        return AmarrEventFrigate.class;
    }

    public static LinkedHashMap<String, AmarrEventFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AmarrEventFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AmarrEventFrigate> items;

    }

}
