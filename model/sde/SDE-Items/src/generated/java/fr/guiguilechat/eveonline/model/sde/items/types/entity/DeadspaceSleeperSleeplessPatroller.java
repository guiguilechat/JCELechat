
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperSleeplessPatroller
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperSleeplessPatroller.yaml";
    private static LinkedHashMap<String, DeadspaceSleeperSleeplessPatroller> cache = (null);

    @Override
    public int getGroupId() {
        return  983;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSleeperSleeplessPatroller.class;
    }

    public static LinkedHashMap<String, DeadspaceSleeperSleeplessPatroller> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperSleeplessPatroller.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceSleeperSleeplessPatroller> items;

    }

}
