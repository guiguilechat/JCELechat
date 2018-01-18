
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionThukkerFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionThukkerFrigate.yaml";
    private static LinkedHashMap<String, MissionThukkerFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  826;
    }

    @Override
    public Class<?> getGroup() {
        return MissionThukkerFrigate.class;
    }

    public static LinkedHashMap<String, MissionThukkerFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionThukkerFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionThukkerFrigate> items;

    }

}
