
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFighterDrone
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionFighterDrone.yaml";
    private static LinkedHashMap<String, MissionFighterDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  861;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFighterDrone.class;
    }

    public static LinkedHashMap<String, MissionFighterDrone> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionFighterDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionFighterDrone> items;

    }

}
