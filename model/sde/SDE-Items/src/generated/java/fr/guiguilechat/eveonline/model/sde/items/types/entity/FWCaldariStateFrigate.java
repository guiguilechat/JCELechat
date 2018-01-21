
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWCaldariStateFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/FWCaldariStateFrigate.yaml";
    private static LinkedHashMap<String, FWCaldariStateFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1167;
    }

    @Override
    public Class<?> getGroup() {
        return FWCaldariStateFrigate.class;
    }

    public static LinkedHashMap<String, FWCaldariStateFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWCaldariStateFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, FWCaldariStateFrigate> items;

    }

}
