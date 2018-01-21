
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperEmergentPatroller
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperEmergentPatroller.yaml";
    private static LinkedHashMap<String, DeadspaceSleeperEmergentPatroller> cache = (null);

    @Override
    public int getGroupId() {
        return  987;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSleeperEmergentPatroller.class;
    }

    public static LinkedHashMap<String, DeadspaceSleeperEmergentPatroller> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperEmergentPatroller.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceSleeperEmergentPatroller> items;

    }

}
