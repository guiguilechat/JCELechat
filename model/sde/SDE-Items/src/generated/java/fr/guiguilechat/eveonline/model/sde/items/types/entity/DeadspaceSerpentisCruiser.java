
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSerpentisCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSerpentisCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceSerpentisCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  631;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSerpentisCruiser.class;
    }

    public static LinkedHashMap<String, DeadspaceSerpentisCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSerpentisCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceSerpentisCruiser> items;

    }

}
