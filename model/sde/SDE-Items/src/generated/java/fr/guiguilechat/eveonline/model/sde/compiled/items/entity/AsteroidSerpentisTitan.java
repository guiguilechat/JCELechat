
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisTitan
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisTitan.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisTitan> cache = (null);

    @Override
    public int getGroupId() {
        return  1690;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisTitan.class;
    }

    public static LinkedHashMap<String, AsteroidSerpentisTitan> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisTitan.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidSerpentisTitan> items;

    }

}
