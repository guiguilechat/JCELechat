
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelTitan
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelTitan.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelTitan> cache = (null);

    @Override
    public int getGroupId() {
        return  1682;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelTitan.class;
    }

    public static LinkedHashMap<String, AsteroidAngelCartelTitan> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelTitan.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidAngelCartelTitan> items;

    }

}
