
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGenericBattleCruisers
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionGenericBattleCruisers.yaml";
    private static LinkedHashMap<String, MissionGenericBattleCruisers> cache = (null);

    @Override
    public int getGroupId() {
        return  828;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGenericBattleCruisers.class;
    }

    public static LinkedHashMap<String, MissionGenericBattleCruisers> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionGenericBattleCruisers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionGenericBattleCruisers> items;

    }

}
