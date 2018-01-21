
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderBattleCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationCommanderBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationCommanderBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  807;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationCommanderBattleCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidSanshaSNationCommanderBattleCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidSanshaSNationCommanderBattleCruiser> items;

    }

}
