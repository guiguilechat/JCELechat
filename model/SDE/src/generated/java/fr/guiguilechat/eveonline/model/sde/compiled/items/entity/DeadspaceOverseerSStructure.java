
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerSStructure
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/DeadspaceOverseerSStructure.yaml";
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
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceOverseerSStructure> items;

    }

}
