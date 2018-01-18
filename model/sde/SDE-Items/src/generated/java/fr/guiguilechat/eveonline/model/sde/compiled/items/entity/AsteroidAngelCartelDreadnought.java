
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelDreadnought
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelDreadnought.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelDreadnought> cache = (null);

    @Override
    public int getGroupId() {
        return  1681;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelDreadnought.class;
    }

    public static LinkedHashMap<String, AsteroidAngelCartelDreadnought> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelDreadnought.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidAngelCartelDreadnought> items;

    }

}
