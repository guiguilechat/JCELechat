
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceRogueDroneBattleCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceRogueDroneBattleCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceRogueDroneBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  801;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceRogueDroneBattleCruiser.class;
    }

    public static LinkedHashMap<String, DeadspaceRogueDroneBattleCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceRogueDroneBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceRogueDroneBattleCruiser> items;

    }

}
