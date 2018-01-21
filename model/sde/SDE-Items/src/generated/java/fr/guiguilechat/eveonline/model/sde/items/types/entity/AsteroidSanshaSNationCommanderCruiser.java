
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationCommanderCruiser.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationCommanderCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  808;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationCommanderCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidSanshaSNationCommanderCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidSanshaSNationCommanderCruiser> items;

    }

}
