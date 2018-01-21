
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationCommanderFrigate.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationCommanderFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  810;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationCommanderFrigate.class;
    }

    public static LinkedHashMap<String, AsteroidSanshaSNationCommanderFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidSanshaSNationCommanderFrigate> items;

    }

}
