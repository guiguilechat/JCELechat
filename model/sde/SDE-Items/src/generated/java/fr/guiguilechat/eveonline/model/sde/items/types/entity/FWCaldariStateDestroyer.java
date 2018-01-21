
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWCaldariStateDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/FWCaldariStateDestroyer.yaml";
    private static LinkedHashMap<String, FWCaldariStateDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  1176;
    }

    @Override
    public Class<?> getGroup() {
        return FWCaldariStateDestroyer.class;
    }

    public static LinkedHashMap<String, FWCaldariStateDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWCaldariStateDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, FWCaldariStateDestroyer> items;

    }

}
