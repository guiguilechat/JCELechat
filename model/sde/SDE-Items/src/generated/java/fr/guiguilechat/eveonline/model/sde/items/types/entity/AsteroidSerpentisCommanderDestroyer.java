
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCommanderDestroyer.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisCommanderDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  813;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisCommanderDestroyer.class;
    }

    public static LinkedHashMap<String, AsteroidSerpentisCommanderDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCommanderDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidSerpentisCommanderDestroyer> items;

    }

}
