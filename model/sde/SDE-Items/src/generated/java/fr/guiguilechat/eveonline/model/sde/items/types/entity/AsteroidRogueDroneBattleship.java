
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneBattleship.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  756;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneBattleship.class;
    }

    public static LinkedHashMap<String, AsteroidRogueDroneBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidRogueDroneBattleship> items;

    }

}
