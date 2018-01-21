
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWMinmatarRepublicDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/FWMinmatarRepublicDestroyer.yaml";
    private static LinkedHashMap<String, FWMinmatarRepublicDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  1178;
    }

    @Override
    public Class<?> getGroup() {
        return FWMinmatarRepublicDestroyer.class;
    }

    public static LinkedHashMap<String, FWMinmatarRepublicDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWMinmatarRepublicDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, FWMinmatarRepublicDestroyer> items;

    }

}
