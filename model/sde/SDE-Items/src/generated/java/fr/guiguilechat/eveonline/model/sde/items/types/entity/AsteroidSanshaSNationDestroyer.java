
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationDestroyer.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  581;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationDestroyer.class;
    }

    public static LinkedHashMap<String, AsteroidSanshaSNationDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidSanshaSNationDestroyer> items;

    }

}
