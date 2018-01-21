
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceGuristasBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceGuristasBattleship.yaml";
    private static LinkedHashMap<String, DeadspaceGuristasBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  612;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceGuristasBattleship.class;
    }

    public static LinkedHashMap<String, DeadspaceGuristasBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceGuristasBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceGuristasBattleship> items;

    }

}
