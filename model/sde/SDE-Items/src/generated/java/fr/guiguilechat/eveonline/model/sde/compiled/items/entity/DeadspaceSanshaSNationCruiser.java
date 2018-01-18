
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSanshaSNationCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSanshaSNationCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceSanshaSNationCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  622;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSanshaSNationCruiser.class;
    }

    public static LinkedHashMap<String, DeadspaceSanshaSNationCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSanshaSNationCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceSanshaSNationCruiser> items;

    }

}
