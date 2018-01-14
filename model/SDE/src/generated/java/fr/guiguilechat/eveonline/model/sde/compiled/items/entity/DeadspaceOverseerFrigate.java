
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/DeadspaceOverseerFrigate.yaml";
    private static LinkedHashMap<String, DeadspaceOverseerFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  819;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceOverseerFrigate.class;
    }

    public static LinkedHashMap<String, DeadspaceOverseerFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceOverseerFrigate> items;

    }

}
