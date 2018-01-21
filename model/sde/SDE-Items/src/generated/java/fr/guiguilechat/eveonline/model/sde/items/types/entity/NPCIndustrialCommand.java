
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCIndustrialCommand
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/NPCIndustrialCommand.yaml";
    private static LinkedHashMap<String, NPCIndustrialCommand> cache = (null);

    @Override
    public int getGroupId() {
        return  1896;
    }

    @Override
    public Class<?> getGroup() {
        return NPCIndustrialCommand.class;
    }

    public static LinkedHashMap<String, NPCIndustrialCommand> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NPCIndustrialCommand.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, NPCIndustrialCommand> items;

    }

}
