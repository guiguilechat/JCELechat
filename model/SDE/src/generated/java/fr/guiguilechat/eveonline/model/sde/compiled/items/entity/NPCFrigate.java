
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/NPCFrigate.yaml";
    private static LinkedHashMap<String, NPCFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1803;
    }

    @Override
    public Class<?> getGroup() {
        return NPCFrigate.class;
    }

    public static LinkedHashMap<String, NPCFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NPCFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, NPCFrigate> items;

    }

}
