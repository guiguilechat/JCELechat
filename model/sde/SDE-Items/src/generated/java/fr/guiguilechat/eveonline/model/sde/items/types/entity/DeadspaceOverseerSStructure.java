
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerSStructure
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceOverseerSStructure.yaml";
    private static LinkedHashMap<String, DeadspaceOverseerSStructure> cache = (null);

    @Override
    public int getGroupId() {
        return  494;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceOverseerSStructure.class;
    }

    public static LinkedHashMap<String, DeadspaceOverseerSStructure> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceOverseerSStructure.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceOverseerSStructure> items;

    }

}
