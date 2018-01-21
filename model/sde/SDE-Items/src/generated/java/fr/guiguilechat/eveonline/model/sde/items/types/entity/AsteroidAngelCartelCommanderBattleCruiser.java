
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelCommanderBattleCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelCommanderBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelCommanderBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  793;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelCommanderBattleCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidAngelCartelCommanderBattleCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidAngelCartelCommanderBattleCruiser> items;

    }

}
