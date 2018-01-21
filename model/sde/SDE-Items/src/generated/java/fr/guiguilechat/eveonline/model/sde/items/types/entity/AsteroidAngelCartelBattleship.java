
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelBattleship.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  552;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelBattleship.class;
    }

    public static LinkedHashMap<String, AsteroidAngelCartelBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidAngelCartelBattleship> items;

    }

}
