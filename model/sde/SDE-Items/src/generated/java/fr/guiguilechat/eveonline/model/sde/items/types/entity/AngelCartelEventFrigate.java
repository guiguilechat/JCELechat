
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AngelCartelEventFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AngelCartelEventFrigate.yaml";
    private static LinkedHashMap<String, AngelCartelEventFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1724;
    }

    @Override
    public Class<?> getGroup() {
        return AngelCartelEventFrigate.class;
    }

    public static LinkedHashMap<String, AngelCartelEventFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AngelCartelEventFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AngelCartelEventFrigate> items;

    }

}
