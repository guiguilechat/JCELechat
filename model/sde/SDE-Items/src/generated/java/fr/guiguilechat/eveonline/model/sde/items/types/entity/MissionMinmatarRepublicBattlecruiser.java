
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicBattlecruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicBattlecruiser.yaml";
    private static LinkedHashMap<String, MissionMinmatarRepublicBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  685;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMinmatarRepublicBattlecruiser.class;
    }

    public static LinkedHashMap<String, MissionMinmatarRepublicBattlecruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionMinmatarRepublicBattlecruiser> items;

    }

}
