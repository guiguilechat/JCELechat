
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSanshaSNationBattleCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSanshaSNationBattleCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceSanshaSNationBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  620;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSanshaSNationBattleCruiser.class;
    }

    public static LinkedHashMap<String, DeadspaceSanshaSNationBattleCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSanshaSNationBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DeadspaceSanshaSNationBattleCruiser> items;

    }

}
