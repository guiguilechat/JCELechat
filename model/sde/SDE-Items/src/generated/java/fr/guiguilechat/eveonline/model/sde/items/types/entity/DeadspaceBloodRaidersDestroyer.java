
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceBloodRaidersDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceBloodRaidersDestroyer.yaml";
    private static LinkedHashMap<String, DeadspaceBloodRaidersDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  605;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceBloodRaidersDestroyer.class;
    }

    public static LinkedHashMap<String, DeadspaceBloodRaidersDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceBloodRaidersDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceBloodRaidersDestroyer> items;

    }

}
