
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationBattleCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/AsteroidSanshaSNationBattleCruiser.yaml";
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
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidSanshaSNationBattleCruiser> items;

    }

}
