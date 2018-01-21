
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSanshaSNationFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSanshaSNationFrigate.yaml";
    private static LinkedHashMap<String, DeadspaceSanshaSNationFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  624;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSanshaSNationFrigate.class;
    }

    public static LinkedHashMap<String, DeadspaceSanshaSNationFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSanshaSNationFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceSanshaSNationFrigate> items;

    }

}
