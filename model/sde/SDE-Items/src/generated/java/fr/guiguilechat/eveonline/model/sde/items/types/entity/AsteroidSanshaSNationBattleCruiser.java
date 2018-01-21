
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationBattleCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  582;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationBattleCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidSanshaSNationBattleCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidSanshaSNationBattleCruiser> items;

    }

}
