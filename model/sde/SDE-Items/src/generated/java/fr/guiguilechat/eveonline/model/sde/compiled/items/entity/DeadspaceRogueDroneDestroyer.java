
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceRogueDroneDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceRogueDroneDestroyer.yaml";
    private static LinkedHashMap<String, DeadspaceRogueDroneDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  804;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceRogueDroneDestroyer.class;
    }

    public static LinkedHashMap<String, DeadspaceRogueDroneDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceRogueDroneDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceRogueDroneDestroyer> items;

    }

}
