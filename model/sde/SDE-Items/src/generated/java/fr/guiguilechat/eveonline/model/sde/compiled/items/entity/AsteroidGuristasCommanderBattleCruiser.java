
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderBattleCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCommanderBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidGuristasCommanderBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  797;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasCommanderBattleCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidGuristasCommanderBattleCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidGuristasCommanderBattleCruiser> items;

    }

}
