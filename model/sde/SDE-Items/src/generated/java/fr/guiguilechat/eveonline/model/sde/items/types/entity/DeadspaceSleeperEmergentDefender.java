
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperEmergentDefender
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperEmergentDefender.yaml";
    private static LinkedHashMap<String, DeadspaceSleeperEmergentDefender> cache = (null);

    @Override
    public int getGroupId() {
        return  986;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSleeperEmergentDefender.class;
    }

    public static LinkedHashMap<String, DeadspaceSleeperEmergentDefender> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperEmergentDefender.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceSleeperEmergentDefender> items;

    }

}
