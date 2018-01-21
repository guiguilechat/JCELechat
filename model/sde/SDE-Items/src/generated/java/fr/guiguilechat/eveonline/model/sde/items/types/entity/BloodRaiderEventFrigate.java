
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class BloodRaiderEventFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/BloodRaiderEventFrigate.yaml";
    private static LinkedHashMap<String, BloodRaiderEventFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1453;
    }

    @Override
    public Class<?> getGroup() {
        return BloodRaiderEventFrigate.class;
    }

    public static LinkedHashMap<String, BloodRaiderEventFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(BloodRaiderEventFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, BloodRaiderEventFrigate> items;

    }

}
