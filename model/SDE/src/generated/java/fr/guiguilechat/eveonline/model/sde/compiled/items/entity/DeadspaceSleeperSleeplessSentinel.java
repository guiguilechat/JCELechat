
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperSleeplessSentinel
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/DeadspaceSleeperSleeplessSentinel.yaml";
    private static LinkedHashMap<String, DeadspaceSleeperSleeplessSentinel> cache = (null);

    @Override
    public int getGroupId() {
        return  959;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSleeperSleeplessSentinel.class;
    }

    public static LinkedHashMap<String, DeadspaceSleeperSleeplessSentinel> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceSleeperSleeplessSentinel> items;

    }

}
