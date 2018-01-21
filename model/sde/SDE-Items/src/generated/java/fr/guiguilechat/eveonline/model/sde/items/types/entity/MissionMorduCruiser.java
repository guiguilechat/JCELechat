
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMorduCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionMorduCruiser.yaml";
    private static LinkedHashMap<String, MissionMorduCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  701;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMorduCruiser.class;
    }

    public static LinkedHashMap<String, MissionMorduCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionMorduCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionMorduCruiser> items;

    }

}
