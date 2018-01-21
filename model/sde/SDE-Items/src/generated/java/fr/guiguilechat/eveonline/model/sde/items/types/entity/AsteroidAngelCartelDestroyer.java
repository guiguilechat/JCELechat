
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelDestroyer.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  575;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelDestroyer.class;
    }

    public static LinkedHashMap<String, AsteroidAngelCartelDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidAngelCartelDestroyer> items;

    }

}
