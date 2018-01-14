
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperSleeplessPatroller
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/DeadspaceSleeperSleeplessPatroller.yaml";
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
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceSleeperSleeplessPatroller> items;

    }

}
