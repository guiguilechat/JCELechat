
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicBattleship.yaml";
    private static LinkedHashMap<String, MissionMinmatarRepublicBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  706;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMinmatarRepublicBattleship.class;
    }

    public static LinkedHashMap<String, MissionMinmatarRepublicBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionMinmatarRepublicBattleship> items;

    }

}
