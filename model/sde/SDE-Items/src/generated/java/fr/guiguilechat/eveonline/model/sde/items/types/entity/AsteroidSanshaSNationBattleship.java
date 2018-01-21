
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationBattleship.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  565;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationBattleship.class;
    }

    public static LinkedHashMap<String, AsteroidSanshaSNationBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidSanshaSNationBattleship> items;

    }

}
