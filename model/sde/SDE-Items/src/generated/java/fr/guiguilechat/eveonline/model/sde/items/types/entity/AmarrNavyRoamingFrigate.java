
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrNavyRoamingFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AmarrNavyRoamingFrigate.yaml";
    private static LinkedHashMap<String, AmarrNavyRoamingFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1414;
    }

    @Override
    public Class<?> getGroup() {
        return AmarrNavyRoamingFrigate.class;
    }

    public static LinkedHashMap<String, AmarrNavyRoamingFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AmarrNavyRoamingFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AmarrNavyRoamingFrigate> items;

    }

}
