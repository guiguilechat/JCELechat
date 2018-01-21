
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateBattleship.yaml";
    private static LinkedHashMap<String, MissionCaldariStateBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  674;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCaldariStateBattleship.class;
    }

    public static LinkedHashMap<String, MissionCaldariStateBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionCaldariStateBattleship> items;

    }

}
