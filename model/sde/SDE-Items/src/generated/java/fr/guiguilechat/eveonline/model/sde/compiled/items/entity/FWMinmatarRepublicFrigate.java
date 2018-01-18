
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWMinmatarRepublicFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/FWMinmatarRepublicFrigate.yaml";
    private static LinkedHashMap<String, FWMinmatarRepublicFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1166;
    }

    @Override
    public Class<?> getGroup() {
        return FWMinmatarRepublicFrigate.class;
    }

    public static LinkedHashMap<String, FWMinmatarRepublicFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWMinmatarRepublicFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, FWMinmatarRepublicFrigate> items;

    }

}
