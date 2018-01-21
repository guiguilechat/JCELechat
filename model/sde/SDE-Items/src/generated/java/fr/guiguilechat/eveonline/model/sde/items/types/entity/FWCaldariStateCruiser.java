
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWCaldariStateCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/FWCaldariStateCruiser.yaml";
    private static LinkedHashMap<String, FWCaldariStateCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1180;
    }

    @Override
    public Class<?> getGroup() {
        return FWCaldariStateCruiser.class;
    }

    public static LinkedHashMap<String, FWCaldariStateCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWCaldariStateCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, FWCaldariStateCruiser> items;

    }

}
